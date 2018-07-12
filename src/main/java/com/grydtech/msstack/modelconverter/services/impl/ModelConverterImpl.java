package com.grydtech.msstack.modelconverter.services.impl;

import com.grydtech.msstack.modelconverter.business.BusinessModel;
import com.grydtech.msstack.modelconverter.business.contract.BusinessContract;
import com.grydtech.msstack.modelconverter.common.Constants;
import com.grydtech.msstack.modelconverter.microservice.MicroServiceModel;
import com.grydtech.msstack.modelconverter.microservice.communication.EventClassSchema;
import com.grydtech.msstack.modelconverter.microservice.communication.RequestClassSchema;
import com.grydtech.msstack.modelconverter.microservice.communication.ResponseClassSchema;
import com.grydtech.msstack.modelconverter.microservice.entity.Attribute;
import com.grydtech.msstack.modelconverter.microservice.handler.HandlerClassSchema;
import com.grydtech.msstack.modelconverter.services.ModelConverter;
import com.grydtech.msstack.modelconverter.common.ModelConverterUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelConverterImpl implements ModelConverter {

    @Override
    public List<MicroServiceModel> convertToMicroServiceModel(BusinessModel businessModel) {
        Map<String, MicroServiceModel> microServiceModelMap = new HashMap<>();

        for (BusinessContract businessContract : businessModel.getContracts()) {
            MicroServiceModel microServiceModel;

            // generate micro service models according to entities (one entity per one micro service)
            if (microServiceModelMap.containsKey(businessContract.getEntityId())) {
                microServiceModel = microServiceModelMap.get(businessContract.getEntityId());
            } else {
                microServiceModel = new MicroServiceModel();
                microServiceModel.setServiceName(businessContract.getEntity().getName() + Constants.SERVICE_SUFFIX);
                microServiceModel.setVersion(businessModel.getVersion());
                microServiceModel.addEntityClassCollection(ModelConverterUtil.extractEntities(businessContract.getEntity()));
                microServiceModelMap.put(businessContract.getEntityId(), microServiceModel);
            }

            HandlerClassSchema handler = new HandlerClassSchema();
            handler.setName(businessContract.getHandler().getName() + Constants.HANDLER_CLASS_SUFFIX);
            handler.setType(businessContract.getHandler().getType());

            // get events from business contract and update class schemas and handlers according to that
            businessContract.getEvents().forEach(event -> {
                // create class for each event
                EventClassSchema eventClass = new EventClassSchema();
                eventClass.setName(event + Constants.EVENT_CLASS_SUFFIX);
                microServiceModel.addEventClass(eventClass);

                // add event apply method to base entity (eg: void apply(OrderCreatedEvent event))
                microServiceModel.getEntityClasses().get(0).addEvent(event + Constants.EVENT_CLASS_SUFFIX);

                // to wire event emitting inside handler
                handler.addEvent(event + Constants.EVENT_CLASS_SUFFIX);
            });

            RequestClassSchema request = new RequestClassSchema();
            request.setName(businessContract.getRequest().getName());
            businessContract.getRequest().getFields().forEach((reqField) -> {
                Attribute attribute = ModelConverterUtil.extractAttribute(reqField, "single");
                request.addAttribute(attribute);
            });

            ResponseClassSchema response = new ResponseClassSchema();
            response.setName(businessContract.getResponse().getName());
            businessContract.getResponse().getFields().forEach((resField) -> {
                Attribute attribute = ModelConverterUtil.extractAttribute(resField, "single");
                response.addAttribute(attribute);
            });

            handler.setConsume(request);
            handler.setProduce(response);

            microServiceModel.addHandler(handler);
        }

        return new ArrayList<>(microServiceModelMap.values());
    }
}
