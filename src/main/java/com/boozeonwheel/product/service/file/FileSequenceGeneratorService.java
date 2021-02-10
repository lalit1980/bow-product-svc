package com.boozeonwheel.product.service.file;

import java.util.Objects;

import com.boozeonwheel.product.repository.category.DatabaseSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class FileSequenceGeneratorService {

	private MongoOperations mongoOperations;
	
	@Autowired
    public FileSequenceGeneratorService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public long generateSequence(String seqName) {
    	Query query = new Query(
                Criteria.where("_id").is(seqName));
        DatabaseSequence counter = mongoOperations.findAndModify(
        		query,
                new Update().inc("seq",1), 
                FindAndModifyOptions.options().returnNew(true).upsert(true),
                DatabaseSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;

    }
}
