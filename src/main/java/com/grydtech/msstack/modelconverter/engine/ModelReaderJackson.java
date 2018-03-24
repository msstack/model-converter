package com.grydtech.msstack.modelconverter.engine;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.grydtech.msstack.modelconverter.business.BusinessContract;
import com.grydtech.msstack.modelconverter.business.BusinessEntity;
import com.grydtech.msstack.modelconverter.business.BusinessModel;
import com.grydtech.msstack.modelconverter.business.EntityField;
import com.grydtech.msstack.modelconverter.business.ContractRequest;
import com.grydtech.msstack.modelconverter.business.ContractResponse;
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

        Map<String, BusinessEntity> entityMap = new HashMap<>();
        Map<String, ContractRequest> requestMap = new HashMap<>();
        Map<String, ContractResponse> responseMap = new HashMap<>();

        assert businessModel != null;
        businessModel.getEntities().forEach(businessEntity -> {
            businessEntity.getFields().forEach(field -> {
                int pos1 = field.getType().indexOf('<');
                int pos2 = field.getType().indexOf('>');
                if (pos1 != -1) {
                    String subEntityId = field.getType().substring(pos1 + 1, pos2);
                    BusinessEntity subEntity = entityMap.get(subEntityId);
                    businessEntity.addSubEntity(subEntity);

                    field.setType(field.getType().replace(subEntityId, subEntity.getName()));
                }
            });
            entityMap.put(businessEntity.getId(), businessEntity);
        });

        businessModel.getRequests().forEach(request -> {
            requestMap.put(request.getId(), request);
        });

        businessModel.getResponses().forEach(response -> {
            responseMap.put(response.getId(), response);
        });

        businessModel.getContracts().forEach(businessContract -> {
            businessContract.setEntity(entityMap.get(businessContract.getEntityId()));
            businessContract.setRequest(requestMap.get(businessContract.getRequestId()));
            businessContract.setResponse(responseMap.get(businessContract.getResponseId()));
        });

        return businessModel;
    }

    public MicroServiceModel readMicroServiceModel(File file) {
        throw new UnsupportedOperationException();
    }
}
