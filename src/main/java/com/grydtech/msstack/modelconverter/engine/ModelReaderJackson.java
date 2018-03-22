package com.grydtech.msstack.modelconverter.engine;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.grydtech.msstack.modelconverter.business.BusinessContract;
import com.grydtech.msstack.modelconverter.business.BusinessEntity;
import com.grydtech.msstack.modelconverter.business.BusinessModel;
import com.grydtech.msstack.modelconverter.business.EntityField;
import com.grydtech.msstack.modelconverter.microservice.MicroServiceModel;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ModelReaderJackson implements ModelReader {
    private static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
    }

    public BusinessModel readBusinessModel(File file) {
        BusinessModel businessModel = null;
        try {
            businessModel = objectMapper.readValue(file, BusinessModel.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, BusinessEntity> entityMap = new HashMap<String, BusinessEntity>();

        assert businessModel != null;
        for (BusinessEntity businessEntity: businessModel.getEntities()) {
            for (EntityField field: businessEntity.getFields()) {
                int pos1 = field.getType().indexOf('<');
                int pos2 = field.getType().indexOf('>');
                if (pos1 != -1) {
                    String subEntityId = field.getType().substring(pos1 + 1, pos2);
                    field.setSubEntity(entityMap.get(subEntityId));
                }
            }
            entityMap.put(businessEntity.getId(), businessEntity);
        }

        for (BusinessContract businessContract: businessModel.getContracts()) {
            String entityId = businessContract.getEntityId();
            businessContract.setEntity(entityMap.get(entityId));
        }

        return businessModel;
    }

    public MicroServiceModel readMicroServiceModel(File file) {
        throw new UnsupportedOperationException();
    }
}
