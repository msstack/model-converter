package com.grydtech.msstack.modelconverter.engine;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.grydtech.msstack.modelconverter.business.BusinessContract;
import com.grydtech.msstack.modelconverter.business.BusinessEntity;
import com.grydtech.msstack.modelconverter.business.BusinessModel;
import com.grydtech.msstack.modelconverter.business.EntityField;
import com.grydtech.msstack.modelconverter.business.Request;
import com.grydtech.msstack.modelconverter.business.Response;
import com.grydtech.msstack.modelconverter.microservice.MicroServiceModel;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
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
        Map<String, Request> requestMap = new HashMap<>();
        Map<String, Response> responseMap = new HashMap<>();

        assert businessModel != null;
        for (BusinessEntity businessEntity : businessModel.getEntities()) {
            for (EntityField field : businessEntity.getFields()) {
                int pos1 = field.getType().indexOf('<');
                if (pos1 != -1) {
                    //pos2 only exists iff pos1 exists
                    int pos2 = field.getType().indexOf('>');
                    String subEntityId = field.getType().substring(pos1 + 1, pos2);
                    //what if at this point subentityis not added in json?
                    BusinessEntity subEntity = entityMap.get(subEntityId);
                    if (subEntity != null) {
                        field.setType(field.getType().replace(subEntityId, subEntity.getName()));
                        businessEntity.addSubEntity(subEntity);
                    }
                    //at a later point, required toadd the subEntity
                }
            }
            entityMap.put(businessEntity.getId(), businessEntity);
        }

        for (Request request : businessModel.getRequests()) {
            requestMap.put(request.getId(), request);
        }

        for (Response response : businessModel.getResponses()) {
            responseMap.put(response.getId(), response);
        }
        for (BusinessContract businessContract : businessModel.getContracts()) {
            businessContract.setEntity(entityMap.get(businessContract.getEntityId()));
            businessContract.setRequest(requestMap.get(businessContract.getRequestId()));
            businessContract.setResponse(responseMap.get(businessContract.getResponseId()));
        }

        return businessModel;
    }

    public MicroServiceModel readMicroServiceModel(File file) {
        throw new UnsupportedOperationException();
    }
}
