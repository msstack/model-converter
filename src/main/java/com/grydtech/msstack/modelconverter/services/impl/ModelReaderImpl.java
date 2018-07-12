package com.grydtech.msstack.modelconverter.services.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.grydtech.msstack.modelconverter.business.contract.BusinessContract;
import com.grydtech.msstack.modelconverter.business.entity.BusinessEntity;
import com.grydtech.msstack.modelconverter.business.BusinessModel;
import com.grydtech.msstack.modelconverter.business.contract.ContractRequest;
import com.grydtech.msstack.modelconverter.business.contract.ContractResponse;
import com.grydtech.msstack.modelconverter.common.Constants;
import com.grydtech.msstack.modelconverter.microservice.MicroServiceModel;
import com.grydtech.msstack.modelconverter.services.ModelReader;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ModelReaderImpl implements ModelReader {

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
        final Map<String, ContractRequest> requestMap = new HashMap<>();
        final Map<String, ContractResponse> responseMap = new HashMap<>();
        final Map<String, BusinessContract> contractMap = new HashMap<>();

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
