package com.boozeonwheel.product.client;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.boozeonwheel.product.domain.file.FileMetaData;
import com.boozeonwheel.product.domain.master.Master;
import com.boozeonwheel.product.domain.url.UrlType;
import com.boozeonwheel.product.exception.file.DataCorruptedException;
import com.boozeonwheel.product.repository.file.FileRepository;
import com.boozeonwheel.product.repository.master.ProductMasterRepository;
import com.boozeonwheel.product.repository.url.UrlTypeRepository;
import com.boozeonwheel.product.service.file.DiskStorageProvider;
import com.boozeonwheel.product.service.file.FileSequenceGeneratorService;

@Service
public class AmazonClient {

	private AmazonS3 s3client;

	@Value("${amazonProperties.endpointUrl}")
	private String endpointUrl;
	@Value("${amazonProperties.bucketName}")
	private String bucketName;
	@Value("${amazonProperties.accessKey}")
	private String accessKey;
	@Value("${amazonProperties.secretKey}")
	private String secretKey;

	@Autowired
	FileRepository fileRepository;
	
	@Autowired
	UrlTypeRepository urlTypeRepo;
	
	@Autowired
	FileSequenceGeneratorService fileSeqGen;
	
	@Autowired
	ProductMasterRepository productMasterRepo;
	
	@Autowired
	DiskStorageProvider storageProvider;

	@PostConstruct
	private void initializeAmazon() {
		AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
		this.s3client = new AmazonS3Client(credentials);
	}

	public String uploadFile(MultipartFile multipartFile, Long productCode, Integer urlTypeId) {
		String fileUrl = new String();
		try {
			File file = convertMultiPartToFile(multipartFile);
			
			String fileName = generateFileName(multipartFile);
			fileUrl = endpointUrl + "/" + bucketName + "/" + fileName;
			uploadFileTos3bucket(fileName, file);
			FileMetaData fileMetaData = new FileMetaData();
			String location = getTargetFileLocation(fileName);
			fileMetaData.setId(fileSeqGen.generateSequence(FileMetaData.SEQUENCE_NAME));
			fileMetaData.setFileName(fileName);
			fileMetaData.setContentSize(multipartFile.getSize());
			fileMetaData.setContentType(multipartFile.getContentType());
			if(multipartFile.getContentType().equalsIgnoreCase("image/jpeg") || multipartFile.getContentType().equalsIgnoreCase("image/jpeg")) {
				BufferedImage bimg = ImageIO.read(file);
				fileMetaData.setAttachmentHeight(bimg.getHeight());
				fileMetaData.setAttachmentWidth(bimg.getWidth());
				fileMetaData.setType("Spree::Image");
			}
			fileMetaData.setViewableId(productCode != null ? productCode.intValue() : null);
			Master master=productMasterRepo.findByProductMasterId(productCode != null ? productCode.intValue() : null);
			if(master!=null) {
				if(!master.getIsMaster()) {
					fileMetaData.setViewableType("Spree::Variant");
				}else {
					fileMetaData.setViewableType("Spree::Master");
				}
			}
			UrlType urlType=urlTypeRepo.findByUrlId(urlTypeId);
			fileMetaData.setUrlTypeId(urlTypeId);
			fileMetaData.setUrlType(urlType.getUrlType());
			fileMetaData.setAlt(master.getName());
			fileMetaData.setPosition(1);
			fileMetaData.setLocation(location);
			fileMetaData.setS3Path(fileUrl);
			fileMetaData.setAttachmentUpdatedAt(new Date());
			fileMetaData.setProductCode(productCode);
			UUID uuid = UUID.randomUUID();
	        String randomUUIDString = uuid.toString();
			fileMetaData.setFileId(randomUUIDString);
			fileRepository.save(fileMetaData);
			file.delete();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileUrl;
	}

	private File convertMultiPartToFile(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}

	private String generateFileName(MultipartFile multiPart) {
		return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
	}

	private void uploadFileTos3bucket(String fileName, File file) {
		s3client.putObject(
				new PutObjectRequest(bucketName, fileName, file).withCannedAcl(CannedAccessControlList.PublicRead));
	}

	public String deleteFileFromS3Bucket(String fileUrl) {
		// String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
		// System.out.println("fileName: "+fileName);
		s3client.deleteObject(new DeleteObjectRequest(bucketName, fileUrl));
		return "Successfully deleted";
	}

	private String getTargetFileLocation(String originalFilename) {
		return storageProvider.getLocation() + File.separator + originalFilename;
	}
	private byte[] extractContent(MultipartFile file) {
		try {
			return file.getBytes();
		} catch (IOException e) {
			throw new DataCorruptedException(e);
		}
	}

}
