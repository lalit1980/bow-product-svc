package com.boozeonwheel.product.repository.file;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.boozeonwheel.product.domain.file.FileMetaData;

public interface FileRepository extends MongoRepository<FileMetaData, Long>, FileRespositoryCustom {
	
}
