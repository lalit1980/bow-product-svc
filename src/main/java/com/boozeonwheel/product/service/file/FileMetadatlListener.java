package com.boozeonwheel.product.service.file;

import com.boozeonwheel.product.domain.file.FileMetaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;

public class FileMetadatlListener extends AbstractMongoEventListener<FileMetaData>{
	private FileSequenceGeneratorService sequenceGeneratorService;
	
	@Autowired
    public FileMetadatlListener(FileSequenceGeneratorService sequenceGeneratorService) {
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<FileMetaData> event) {
        if (event.getSource().getId() < 1) {
            event.getSource().setId(sequenceGeneratorService.generateSequence(FileMetaData.SEQUENCE_NAME));
        }
    }
}
