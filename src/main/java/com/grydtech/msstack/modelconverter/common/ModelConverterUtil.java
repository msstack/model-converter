package com.grydtech.msstack.modelconverter.common;

import com.grydtech.msstack.modelconverter.business.Field;
import com.grydtech.msstack.modelconverter.business.communication.BusinessEvent;
import com.grydtech.msstack.modelconverter.business.communication.BusinessRequest;
import com.grydtech.msstack.modelconverter.business.communication.BusinessResponse;
import com.grydtech.msstack.modelconverter.business.contract.BusinessContract;
import com.grydtech.msstack.modelconverter.business.entity.BusinessEntity;
import com.grydtech.msstack.modelconverter.microservice.Attribute;
import com.grydtech.msstack.modelconverter.microservice.communication.CommunicationClass;
import com.grydtech.msstack.modelconverter.microservice.communication.EventClass;
import com.grydtech.msstack.modelconverter.microservice.communication.RequestClass;
import com.grydtech.msstack.modelconverter.microservice.communication.ResponseClass;
import com.grydtech.msstack.modelconverter.microservice.entity.EntityClass;
import com.grydtech.msstack.modelconverter.microservice.handler.HandlerClass;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class ModelConverterUtil {

    private ModelConverterUtil() {
    }

    // recursively search for entities (sub entities) and create class schemas
    public static Collection<BusinessEntity> extractEntities(Collection<BusinessEntity> baseEntities) {
        final List<BusinessEntity> entities = new ArrayList<>(baseEntities);

        for (int i = 0; i < entities.size(); i++) {
            BusinessEntity entity = entities.get(i);
            entity.getFields().stream().filter(field -> field.getEntity() != null).forEach(field -> entities.add(field.getEntity()));
        }

        return entities;
    }

    public static Collection<BusinessEvent> extractEvents(Collection<BusinessEntity> entities, Collection<BusinessContract> contracts) {
        final List<BusinessEvent> events = new ArrayList<>();

        entities.forEach(businessEntity -> {
            events.addAll(businessEntity.getEvents());
        });

        contracts.forEach(businessContract -> {
            if (businessContract.getHandler().getType().equals(Constants.EVENT_HANDLER_TYPE)) {
                BusinessEvent event = (BusinessEvent) businessContract.getRequest();
                events.add(event);
            }

            events.addAll(businessContract.getEvents());
        });

        return events;
    }

    public static EntityClass generateEntityClassSchema(BusinessEntity businessEntity, boolean mainEntity) {
        EntityClass entityClass = new EntityClass();
        entityClass.setId(businessEntity.getId());
        entityClass.setName(businessEntity.getName() + Constants.ENTITY_CLASS_SUFFIX);
        entityClass.setMainEntity(mainEntity);
        businessEntity.getFields().forEach(field -> {
            entityClass.addAttribute(generateClassAttribute(field));
        });
        businessEntity.getEvents().forEach(event -> {
            entityClass.addEvent(generateEventClassSchema(event));
        });

        return entityClass;
    }

    public static HandlerClass generateHandlerClassSchema(BusinessContract businessContract) {
        CommunicationClass requestClassSchema;
        CommunicationClass responseClassSchema;

        if (businessContract.getHandler().getType().equals(Constants.EVENT_HANDLER_TYPE)) {
            requestClassSchema = generateEventClassSchema((BusinessEvent) businessContract.getRequest());
            responseClassSchema = null;
        } else {
            requestClassSchema = generateRequestClassSchema((BusinessRequest) businessContract.getRequest());
            responseClassSchema = generateResponseClassSchema((BusinessResponse) businessContract.getResponse());
        }

        HandlerClass handlerClass = new HandlerClass();
        handlerClass.setId(businessContract.getId());
        handlerClass.setName(businessContract.getHandler().getName() + Constants.HANDLER_CLASS_SUFFIX);
        handlerClass.setType(businessContract.getHandler().getType());
        handlerClass.setRequestClass(requestClassSchema);
        handlerClass.setResponseClass(responseClassSchema);
        handlerClass.setEndPoint(businessContract.getEntity().getName() + "/" + businessContract.getHandler().getName());

        businessContract.getEvents().forEach(event -> {
            handlerClass.addEvent(generateEventClassSchema(event));
        });

        return handlerClass;
    }

    public static RequestClass generateRequestClassSchema(BusinessRequest businessRequest) {
        if (businessRequest == null) return null;

        RequestClass requestClassSchema = new RequestClass();
        requestClassSchema.setId(businessRequest.getId());
        requestClassSchema.setName(businessRequest.getName() + Constants.REQUEST_CLASS_SUFFIX);
        businessRequest.getFields().forEach(field -> requestClassSchema.addAttribute(generateClassAttribute(field)));

        return requestClassSchema;
    }

    public static ResponseClass generateResponseClassSchema(BusinessResponse businessResponse) {
        if (businessResponse == null) return null;

        ResponseClass responseClassSchema = new ResponseClass();
        responseClassSchema.setId(businessResponse.getId());
        responseClassSchema.setName(businessResponse.getName() + Constants.RESPONSE_CLASS_SUFFIX);
        businessResponse.getFields().forEach(field -> responseClassSchema.addAttribute(generateClassAttribute(field)));

        return responseClassSchema;
    }

    public static EventClass generateEventClassSchema(BusinessEvent businessEvent) {
        if (businessEvent == null) return null;

        EventClass eventClassSchema = new EventClass();
        eventClassSchema.setId(businessEvent.getId());
        eventClassSchema.setName(businessEvent.getName() + Constants.EVENT_CLASS_SUFFIX);
        eventClassSchema.setEventGroup(businessEvent.getEventGroup());
        businessEvent.getFields().forEach(field -> eventClassSchema.addAttribute(generateClassAttribute(field)));

        return eventClassSchema;
    }

    public static Attribute generateClassAttribute(Field field) {
        Attribute attribute = new Attribute();
        attribute.setName(field.getName());
        attribute.setArray(field.isArray());
        if (field.getEntity() != null) {
            attribute.setType(field.getEntity().getName() + Constants.ENTITY_CLASS_SUFFIX);
            attribute.setEntity(true);
        } else {
            attribute.setType(field.getType());
            attribute.setEntity(false);
        }

        return attribute;
    }
}
