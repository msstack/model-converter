package com.grydtech.msstack.modelconverter.services.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.grydtech.msstack.modelconverter.components.Contract;
import com.grydtech.msstack.modelconverter.components.Event;
import com.grydtech.msstack.modelconverter.components.Entity;
import com.grydtech.msstack.modelconverter.models.BusinessModel;
import com.grydtech.msstack.modelconverter.components.Request;
import com.grydtech.msstack.modelconverter.components.Response;
import com.grydtech.msstack.modelconverter.utils.Constants;
import com.grydtech.msstack.modelconverter.models.MicroServiceModel;
import com.grydtech.msstack.modelconverter.services.ModelReader;
import com.grydtech.msstack.modelconverter.utils.Helpers;

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

        final Map<String, Entity> entityMap = new HashMap<>();
        final Map<String, Request> requestMap = new HashMap<>();
        final Map<String, Response> responseMap = new HashMap<>();
        final Map<String, Contract> contractMap = new HashMap<>();
        final Map<String, Event> eventMap = new HashMap<>();

        assert businessModel != null;

        // add all components to hash maps (support further mappings)
        businessModel.getEntities().forEach(entity -> entityMap.put(entity.getName(), entity));
        businessModel.getRequests().forEach(request -> requestMap.put(request.getName(), request));
        businessModel.getResponses().forEach(response -> responseMap.put(response.getName(), response));
        businessModel.getEvents().forEach(event -> eventMap.put(event.getName(), event));

        entityMap.values().forEach(entity -> entity.getFields().forEach(field -> {
            Constants.FIELD_TYPE type = Helpers.extractType(field.getTypeRef());
            if (type == Constants.FIELD_TYPE.ENTITY) field.setComponent(entityMap.get(Helpers.extractName(field.getTypeRef())));
        }));

        eventMap.values().forEach(event -> event.getFields().forEach(field -> {
            Constants.FIELD_TYPE type = Helpers.extractType(field.getTypeRef());
            if (type == Constants.FIELD_TYPE.ENTITY) field.setComponent(entityMap.get(Helpers.extractName(field.getTypeRef())));
        }));

        requestMap.values().forEach(request -> request.getFields().forEach(field -> {
            Constants.FIELD_TYPE type = Helpers.extractType(field.getTypeRef());
            if (type == Constants.FIELD_TYPE.ENTITY) field.setComponent(entityMap.get(Helpers.extractName(field.getTypeRef())));
        }));

        responseMap.values().forEach(response -> response.getFields().forEach(field -> {
            Constants.FIELD_TYPE type = Helpers.extractType(field.getTypeRef());
            if (type == Constants.FIELD_TYPE.ENTITY) field.setComponent(entityMap.get(Helpers.extractName(field.getTypeRef())));
        }));

        businessModel.getContracts().forEach(contract -> {
            contract.setEntity(entityMap.get(Helpers.extractName(contract.getEntityRef())));

            Constants.FIELD_TYPE consumesType = Helpers.extractType(contract.getConsumesRef());

            switch (consumesType) {
                case REQUEST: contract.setConsumes(requestMap.get(Helpers.extractName(contract.getConsumesRef()))); break;
                case EVENT: contract.setConsumes(eventMap.get(Helpers.extractName(contract.getConsumesRef()))); break;
            }

            contract.getProducesOnSuccessRefs().forEach(ref -> {
                Constants.FIELD_TYPE producesType = Helpers.extractType(contract.getConsumesRef());

                getCommunicationObject(requestMap, eventMap, contract, producesType);
            });

            contract.getProducesOnErrorRefs().forEach(ref -> {
                Constants.FIELD_TYPE producesType = Helpers.extractType(contract.getConsumesRef());

                getCommunicationObject(requestMap, eventMap, contract, producesType);
            });

            contractMap.put(contract.getName(), contract);
        });

        return businessModel;
    }

    public MicroServiceModel readMicroServiceModel(File file) {
        throw new UnsupportedOperationException();
    }

    private void getCommunicationObject(Map<String, Request> requestMap, Map<String, Event> eventMap, Contract contract, Constants.FIELD_TYPE producesType) {
        switch (producesType) {
            case RESPONSE: contract.addProducesOnSuccess(requestMap.get(Helpers.extractName(contract.getConsumesRef()))); break;
            case EVENT: contract.addProducesOnSuccess(eventMap.get(Helpers.extractName(contract.getConsumesRef()))); break;
        }
    }
}
