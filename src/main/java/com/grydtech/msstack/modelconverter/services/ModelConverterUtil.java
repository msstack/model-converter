package com.grydtech.msstack.modelconverter.services;

import com.grydtech.msstack.modelconverter.business.Field;
import com.grydtech.msstack.modelconverter.business.communication.BusinessEvent;
import com.grydtech.msstack.modelconverter.business.communication.BusinessRequest;
import com.grydtech.msstack.modelconverter.business.communication.BusinessResponse;
import com.grydtech.msstack.modelconverter.business.contract.BusinessContract;
import com.grydtech.msstack.modelconverter.business.entity.BusinessEntity;
import com.grydtech.msstack.modelconverter.common.Constants;
import com.grydtech.msstack.modelconverter.microservice.Attribute;
import com.grydtech.msstack.modelconverter.microservice.communication.EventClass;
import com.grydtech.msstack.modelconverter.microservice.communication.RequestClass;
import com.grydtech.msstack.modelconverter.microservice.communication.ResponseClass;
import com.grydtech.msstack.modelconverter.microservice.entity.EntityClass;
import com.grydtech.msstack.modelconverter.microservice.handler.HandlerClass;

import java.util.*;

public final class ModelConverterUtil {

    private ModelConverterUtil() {
    }

    // recursively search for entities (sub entities) and create class schemas
    public static Collection<BusinessEntity> extractEntities(Collection<BusinessEntity> baseEntities) {
        // ToDo: Validations not implemented
        final Map<String, BusinessEntity> entityMap = new HashMap<>();
        final List<BusinessEntity> entities = new ArrayList<>(baseEntities);

        while (!entities.isEmpty()) {
            BusinessEntity entity = entities.get(0);
            entity.getFields().stream().filter(field -> field.getEntity() != null).forEach(field -> entities.add(field.getEntity()));

            if (!entityMap.containsKey(entity.getId())) {
                entityMap.put(entity.getId(), entity);
            }

            entities.remove(0);
        }

        return entityMap.values();
    }

    public static Collection<BusinessRequest> extractRequests(Collection<BusinessRequest> requests) {
        final Map<String, BusinessRequest> requestMap = new HashMap<>();

        requests.forEach(request -> {
            if (!requestMap.containsKey(request.getId())) {
                requestMap.put(request.getId(), request);
            }
        });

        return requestMap.values();
    }

    public static Collection<BusinessResponse> extractResponses(Collection<BusinessResponse> responses) {
        final Map<String, BusinessResponse> responseMap = new HashMap<>();

        responses.forEach(response -> {
            if (!responseMap.containsKey(response.getId())) {
                responseMap.put(response.getId(), response);
            }
        });

        return responseMap.values();
    }

    public static Collection<BusinessEvent> extractEvents(Collection<BusinessEntity> entities, Collection<BusinessContract> contracts) {
        final Map<String, BusinessEvent> eventMap = new HashMap<>();

        entities.stream().filter(BusinessEntity::isMainEntity).forEach(businessEntity -> {
            businessEntity.getEvents().forEach(event -> {
                if (!eventMap.containsKey(event.getId())) {
                    eventMap.put(event.getId(), event);
                }
            });
        });

        contracts.forEach(businessContract -> {
            businessContract.getEvents().forEach(event -> {
                if (!eventMap.containsKey(event.getId())) {
                    eventMap.put(event.getId(), event);
                }
            });
        });

        return eventMap.values();
    }

    public static EntityClass generateEntityClassSchema(BusinessEntity businessEntity) {
        EntityClass entityClass = new EntityClass();
        entityClass.setId(businessEntity.getId());
        entityClass.setName(businessEntity.getName() + Constants.ENTITY_CLASS_SUFFIX);
        entityClass.setMainEntity(businessEntity.isMainEntity());
        businessEntity.getFields().forEach(field -> {
            entityClass.addAttribute(generateClassAttribute(field));
        });
        businessEntity.getEvents().forEach(event -> {
            entityClass.addEvent(generateEventClassSchema(event));
        });

        return entityClass;
    }

    public static HandlerClass generateHandlerClassSchema(BusinessContract businessContract) {
        RequestClass requestClassSchema = generateRequestClassSchema(businessContract.getRequest());
        ResponseClass responseClassSchema = generateResponseClassSchema(businessContract.getResponse());

        HandlerClass handlerClass = new HandlerClass();
        handlerClass.setId(businessContract.getId());
        handlerClass.setName(businessContract.getHandler().getName() + Constants.HANDLER_CLASS_SUFFIX);
        handlerClass.setType(businessContract.getHandler().getType());
        handlerClass.setRequestClass(requestClassSchema);
        handlerClass.setResponseClass(responseClassSchema);

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
        businessEvent.getFields().forEach(field -> eventClassSchema.addAttribute(generateClassAttribute(field)));

        return eventClassSchema;
    }

    public static Attribute generateClassAttribute(Field field) {
        Attribute attribute = new Attribute();
        attribute.setName(field.getName());
        attribute.setArray(field.isArray());
        if (field.getEntity() != null) {
            attribute.setType(field.getEntity().getName());
            attribute.setEntity(true);
        } else {
            attribute.setType(field.getType());
            attribute.setEntity(false);
        }

        return attribute;
    }
}
