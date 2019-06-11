package com.boozeonwheel.product.repository.liquor;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.boozeonwheel.product.domain.file.FileMetaData;
import com.boozeonwheel.product.domain.liquor.M_LIQUOR;

public interface LiquorRepository extends MongoRepository<M_LIQUOR, Long>, LiquorRespositoryCustom {
	@Query("{'M_LIQUOR.FileMetaData': ?0}")
	List<FileMetaData> findByFileName(String fileName);
	
	@Query("{'M_LIQUOR.FileMetaData': ?0}")
	List<FileMetaData> findByLiquorCodeMeta(long liquorCode);
	
}
