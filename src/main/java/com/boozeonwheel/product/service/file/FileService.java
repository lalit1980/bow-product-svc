package com.boozeonwheel.product.service.file;

import java.io.File;
import java.io.IOException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.boozeonwheel.product.binstore.file.StorageProvider;
import com.boozeonwheel.product.domain.file.FileMetaData;
import com.boozeonwheel.product.exception.file.DataAlreadyExistsException;
import com.boozeonwheel.product.exception.file.DataCorruptedException;
import com.boozeonwheel.product.repository.file.FileRepository;

import lombok.extern.slf4j.Slf4j;


@Service
public class FileService {

    private final StorageProvider storageProvider;
    private final FileRepository fileMetaDataRepository;

    @Autowired
    public FileService(FileRepository fileMetaDataRepository, StorageProvider storageProvider) {
        this.fileMetaDataRepository = fileMetaDataRepository;
        this.storageProvider = storageProvider;
    }

    
    public FileMetaData storeData(MultipartFile file, String title, String details, String s3Path, long LIQUOR_CODE) {
        String fileName = file.getOriginalFilename();
        List<FileMetaData> metadataFromDb = fileMetaDataRepository.findByFileName(fileName);
        if (metadataFromDb!=null && metadataFromDb.size()>0) {
            throw new DataAlreadyExistsException("File with name " + fileName + " has been already uploaded");
        }
        FileMetaData fileMetaData=new FileMetaData();
        String location = getTargetFileLocation(file.getOriginalFilename());
        fileMetaData.setFileName(file.getOriginalFilename());
        fileMetaData.setContentSize(file.getSize());
        fileMetaData.setContentType(file.getContentType());
        fileMetaData.setLocation(location);
        fileMetaData.setS3Path(s3Path);
        fileMetaData.setLIQUOR_CODE(LIQUOR_CODE);
        FileMetaData savedData = fileMetaDataRepository.save(fileMetaData);
        byte[] content = extractContent(file);
        storageProvider.store(file.getOriginalFilename(), content);
        return savedData;
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
