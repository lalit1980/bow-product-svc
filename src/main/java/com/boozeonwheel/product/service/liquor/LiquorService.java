package com.boozeonwheel.product.service.liquor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.boozeonwheel.product.binstore.file.StorageProvider;
import com.boozeonwheel.product.client.AmazonClient;
import com.boozeonwheel.product.domain.file.FileMetaData;
import com.boozeonwheel.product.domain.liquor.M_LIQUOR;
import com.boozeonwheel.product.exception.file.DataAlreadyExistsException;
import com.boozeonwheel.product.exception.file.DataCorruptedException;
import com.boozeonwheel.product.repository.file.FileRepository;
import com.boozeonwheel.product.repository.liquor.LiquorRepository;


@Service
public class LiquorService {

    private final StorageProvider storageProvider;
    private final FileRepository fileMetaDataRepository;
    private final LiquorRepository liquorRepository;
    
    private final AmazonClient amazonService;

    @Autowired
    public LiquorService(FileRepository fileMetaDataRepository, StorageProvider storageProvider,LiquorRepository liquorRepository,AmazonClient amazonService) {
        this.fileMetaDataRepository = fileMetaDataRepository;
        this.storageProvider = storageProvider;
        this.liquorRepository=liquorRepository;
        this.amazonService=amazonService;
    }

    
    public void storeData(MultipartFile file,   M_LIQUOR liquor) {
    	String fileName = liquor.getLIQUOR_CODE()+"_"+file.getOriginalFilename();
    	List<M_LIQUOR> listLiquor=liquorRepository.findByLiquorCode(liquor.getLIQUOR_CODE());
    	M_LIQUOR newLiquor=new M_LIQUOR();
		newLiquor.setIS_ACTIVE(liquor.getIS_ACTIVE());
		newLiquor.setLIQUOR_CODE(liquor.getLIQUOR_CODE());
		newLiquor.setLIQUOR_DESCRIPTION(liquor.getLIQUOR_DESCRIPTION());
		newLiquor.setLIQUOR_SUPPLIER(liquor.getLIQUOR_SUPPLIER());
		newLiquor.setLIQUOR_TYPE(liquor.getLIQUOR_TYPE());
		newLiquor.setMEASUREMENT(liquor.getMEASUREMENT());
		newLiquor.setQUANTITY(liquor.getQUANTITY());
    	if(listLiquor==null || listLiquor.size()==0) {
    		FileMetaData fileMetaData=new FileMetaData();
            String location = getTargetFileLocation(fileName);
            fileMetaData.setFileName(fileName);
            fileMetaData.setContentSize(file.getSize());
            fileMetaData.setContentType(file.getContentType());
            fileMetaData.setLocation(location);
            fileMetaData.setS3Path(amazonService.uploadFile(file));
            fileMetaData.setLIQUOR_CODE(liquor.getLIQUOR_CODE());
            List<FileMetaData> fileMetaDataList=new ArrayList<FileMetaData>();
            fileMetaDataList.add(fileMetaData);
            newLiquor.setFileMetaData(fileMetaDataList);
            byte[] content = extractContent(file);
            storageProvider.store(fileName, content);
            liquorRepository.save(newLiquor);
    	}else {
            if (listLiquor!=null && listLiquor.get(0)!=null && listLiquor.get(0).getFileMetaData()!=null && listLiquor.get(0).getFileMetaData().size()>0) {
            	for(int i=0;i<listLiquor.get(0).getFileMetaData().size();i++) {
            		FileMetaData obj=listLiquor.get(0).getFileMetaData().get(i);
            		if(obj.getFileName().equalsIgnoreCase(fileName)) {
            			throw new DataAlreadyExistsException("File with name " + fileName + " has been already uploaded");
            		}
            	}
            	FileMetaData fileMetaData=new FileMetaData();
                String location = getTargetFileLocation(fileName);
                fileMetaData.setFileName(fileName);
                fileMetaData.setContentSize(file.getSize());
                fileMetaData.setContentType(file.getContentType());
                fileMetaData.setLocation(location);
                fileMetaData.setS3Path(amazonService.uploadFile(file));
                fileMetaData.setLIQUOR_CODE(liquor.getLIQUOR_CODE());
                listLiquor.get(0).getFileMetaData().add(fileMetaData);
                newLiquor.setFileMetaData(listLiquor.get(0).getFileMetaData());
                byte[] content = extractContent(file);
                storageProvider.store(fileName, content);
                liquorRepository.updateLiquor(newLiquor);
            }
    	}
    }
    
    public Page<FileMetaData> getAllMetaData(Pageable pageable) {
        return fileMetaDataRepository.findAll(pageable);
    }

    private byte[] extractContent(MultipartFile file) {
        try {
            return file.getBytes();
        } catch (IOException e) {
            throw new DataCorruptedException(e);
        }
    }

    private String getTargetFileLocation(String originalFilename) {
        return storageProvider.getLocation() + File.separator + originalFilename;
    }
}
