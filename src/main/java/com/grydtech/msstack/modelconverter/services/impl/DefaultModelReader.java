package com.grydtech.msstack.modelconverter.services.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.grydtech.msstack.modelconverter.components.*;
import com.grydtech.msstack.modelconverter.models.BusinessModel;
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
        final Map<String, Event> eventMap = new HashMap<>();

        assert businessModel != null;

        // add all components to hash maps (support further mappings)
        businessModel.getEntities().forEach(entity -> entityMap.put(entity.getName(), entity));
        businessModel.getRequests().forEach(request -> requestMap.put(request.getName(), request));
        businessModel.getResponses().forEach(response -> responseMap.put(response.getName(), response));
        businessModel.getEvents().forEach(event -> eventMap.put(event.getName(), event));

        // inject field properties
        entityMap.values().forEach(entity -> {
            entity.getFields().forEach(field -> Helpers.fillFieldProperties(entityMap, requestMap, responseMap, eventMap, field));
        });
        eventMap.values().forEach(event -> {
            event.getFields().forEach(field -> Helpers.fillFieldProperties(entityMap, requestMap, responseMap, eventMap, field));
        });
        requestMap.values().forEach(request -> {
            request.getFields().forEach(field -> Helpers.fillFieldProperties(entityMap, requestMap, responseMap, eventMap, field));
        });
        responseMap.values().forEach(response -> {
            response.getFields().forEach(field -> Helpers.fillFieldProperties(entityMap, requestMap, responseMap, eventMap, field));
        });

        // add events inside entity
        eventMap.values().forEach(event -> entityMap.get(Helpers.extractName(event.getEntityRef())).addEvent(event));

        // inject contract properties
        businessModel.getContracts().forEach(contract -> {
            contract.setEntity(entityMap.get(Helpers.extractName(contract.getEntityRef())));
            contract.setConsumes(Helpers.getCommunicationObject(requestMap, responseMap, eventMap, contract.getConsumesRef()));
            contract.getProducesOnSuccessRefs().forEach(ref -> contract.addProducesOnSuccess(Helpers.getCommunicationObject(requestMap, responseMap, eventMap, ref)));
            contract.getProducesOnErrorRefs().forEach(ref -> contract.addProducesOnError(Helpers.getCommunicationObject(requestMap, responseMap, eventMap, ref)));
        });

        return businessModel;
    }

    public MicroServiceModel readMicroServiceModel(File file) {
        throw new UnsupportedOperationException();
    }
}
