package com.boozeonwheel.product.service.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import lombok.extern.slf4j.Slf4j;


@Service
public class FileService {

	 	@Autowired
	    private  StorageProvider storageProvider;
	 	
	 	@Autowired
	 	private  FileRepository fileMetaDataRepository;
	 	
	 	@Autowired
	 	LiquorRepository liquorRepository;
	    
	 	@Autowired
	    AmazonClient amazonService;

	       
    public M_LIQUOR storeData(MultipartFile file,   M_LIQUOR liquor) {
        String fileName = liquor.getLIQUOR_CODE()+"_"+file.getOriginalFilename();
        List<FileMetaData> metadataFromDb=liquorRepository.findByFileName(fileName);
       
        if (metadataFromDb!=null && metadataFromDb.size()>0) {
            throw new DataAlreadyExistsException("File with name " + fileName + " has been already uploaded");
        }
        FileMetaData fileMetaData=new FileMetaData();
        String location = getTargetFileLocation(file.getOriginalFilename());
        fileMetaData.setFileName(fileName);
        fileMetaData.setContentSize(file.getSize());
        fileMetaData.setContentType(file.getContentType());
        fileMetaData.setLocation(location);
        fileMetaData.setS3Path(amazonService.uploadFile(file));
        fileMetaData.setLIQUOR_CODE(liquor.getLIQUOR_CODE());
        List<FileMetaData> exitingList=liquorRepository.findByLiquorCodeMeta(liquor.getLIQUOR_CODE());
        if(exitingList==null) {
        	exitingList=new ArrayList<FileMetaData>();
        	exitingList.add(fileMetaData);
        }else {
        	exitingList.add(fileMetaData);
        }
        liquor.setFileMetaData(exitingList);
        liquorRepository.save(liquor);
        byte[] content = extractContent(file);
        storageProvider.store(file.getOriginalFilename(), content);
        return liquor;
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
