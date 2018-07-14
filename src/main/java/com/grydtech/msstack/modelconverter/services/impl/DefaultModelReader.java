package com.grydtech.msstack.modelconverter.services.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.grydtech.msstack.modelconverter.business.contract.BusinessContract;
import com.grydtech.msstack.modelconverter.business.communication.BusinessEvent;
import com.grydtech.msstack.modelconverter.business.entity.BusinessEntity;
import com.grydtech.msstack.modelconverter.business.BusinessModel;
import com.grydtech.msstack.modelconverter.business.communication.BusinessRequest;
import com.grydtech.msstack.modelconverter.business.communication.BusinessResponse;
import com.grydtech.msstack.modelconverter.common.Constants;
import com.grydtech.msstack.modelconverter.microservice.MicroServiceModel;
import com.grydtech.msstack.modelconverter.services.ModelReader;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DefaultModelReader implements ModelReader {

    private static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public BusinessModel readBusinessModel(File file) {
        BusinessModel businessModel = null;
        try {
            businessModel = objectMapper.readValue(file, BusinessModel.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        final Map<String, BusinessEntity> entityMap = new HashMap<>();
        final Map<String, BusinessRequest> requestMap = new HashMap<>();
        final Map<String, BusinessResponse> responseMap = new HashMap<>();
        final Map<String, BusinessContract> contractMap = new HashMap<>();
        final Map<String, BusinessEvent> eventMap = new HashMap<>();

        assert businessModel != null;

        businessModel.getEntities().forEach(businessEntity -> {
            entityMap.put(businessEntity.getId(), businessEntity);
        });

        entityMap.values().forEach(businessEntity -> {
            businessEntity.getFields().stream().filter(field -> !Constants.BASE_TYPES.contains(field.getType())).forEach(field -> field.setEntity(entityMap.get(field.getType())));
        });

        businessModel.getRequests().forEach(request -> {
            request.getFields().stream().filter(field -> !Constants.BASE_TYPES.contains(field.getType())).forEach(field -> field.setEntity(entityMap.get(field.getType())));
            requestMap.put(request.getId(), request);
        });

        businessModel.getResponses().forEach(response -> {
            response.getFields().stream().filter(field -> !Constants.BASE_TYPES.contains(field.getType())).forEach(field -> field.setEntity(entityMap.get(field.getType())));
            responseMap.put(response.getId(), response);
        });

        businessModel.getEvents().forEach(event -> {
            event.getFields().stream().filter(field -> !Constants.BASE_TYPES.contains(field.getType())).forEach(field -> field.setEntity(entityMap.get(field.getType())));
            entityMap.get(event.getEntityId()).addEvent(event);
            eventMap.put(event.getId(), event);
        });

        businessModel.getContracts().forEach(businessContract -> {
            entityMap.get(businessContract.getEntityId()).setMainEntity(true);
            businessContract.setEntity(entityMap.get(businessContract.getEntityId()));
            businessContract.setRequest(requestMap.get(businessContract.getRequestId()));
            businessContract.setResponse(responseMap.get(businessContract.getResponseId()));
            businessContract.getEventIds().forEach(id -> {
                businessContract.addEvent(eventMap.get(id));
            });
            contractMap.put(businessContract.getId(), businessContract);
        });

        businessModel.getServers().forEach(businessServer -> {
            businessServer.getContractIds().forEach(id -> {
                businessServer.addContract(contractMap.get(id));
            });
        });

        return businessModel;
    }

    public MicroServiceModel readMicroServiceModel(File file) {
        throw new UnsupportedOperationException();
    }
}
