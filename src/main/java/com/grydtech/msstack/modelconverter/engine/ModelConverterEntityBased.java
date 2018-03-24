package com.grydtech.msstack.modelconverter.engine;

import com.grydtech.msstack.modelconverter.business.BusinessContract;
import com.grydtech.msstack.modelconverter.business.BusinessEntity;
import com.grydtech.msstack.modelconverter.business.BusinessModel;
import com.grydtech.msstack.modelconverter.business.EntityField;
import com.grydtech.msstack.modelconverter.common.Constants;
import com.grydtech.msstack.modelconverter.microservice.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelConverterEntityBased implements ModelConverter {

    public List<MicroServiceModel> convertToMicroServiceModel(BusinessModel businessModel) {
        Map<String, MicroServiceModel> microServiceModelMap = new HashMap<>();

        for (BusinessContract businessContract : businessModel.getContracts()) {
            MicroServiceModel microServiceModel;

            // generate micro service models according to entities (one entity per one micro service)
            if (microServiceModelMap.containsKey(businessContract.getEntityId())) {
                microServiceModel = microServiceModelMap.get(businessContract.getEntityId());
            } else {
                microServiceModel = new MicroServiceModel(businessContract.getEntity().getName() + Constants.SERVICE_SUFFIX);
                microServiceModel.setVersion(businessModel.getVersion());
                microServiceModel.addEntityClassCollection(this.extractEntities(businessContract.getEntity()));
                microServiceModelMap.put(businessContract.getEntityId(), microServiceModel);
            }

            HandlerClassSchema handler = new HandlerClassSchema(businessContract.getHandler().getName() + Constants.HANDLER_CLASS_SUFFIX, businessContract.getHandler().getType());

            // get events from business contract and update class schemas and handlers according to that
            for (String event : businessContract.getEvents()) {
                // create class for each event
                EventClassSchema eventClass = new EventClassSchema(event + Constants.EVENT_CLASS_SUFFIX);
                microServiceModel.addEventClass(eventClass);

                // add event apply method to base entity (eg: void apply(OrderCreatedEvent event))
                microServiceModel.getEntityClasses().get(0).addEvent(event);

                // to wire event emitting inside handler
                handler.addEvent(event);
            }

            microServiceModel.addHandler(handler);
        }

        return new ArrayList<MicroServiceModel>(microServiceModelMap.values());
    }

    // recursively search for entities (sub entities) and create class schemas
    private List<EntityClassSchema> extractEntities(BusinessEntity baseEntity) {
        // ToDo: Validations not implemented
        List<EntityClassSchema> entityClasses = new ArrayList<>();
        List<BusinessEntity> entities = new ArrayList<>();
        entities.add(baseEntity);

        while (!entities.isEmpty()) {
            BusinessEntity entity = entities.get(0);
            entities.addAll(entity.getSubEntities());

            EntityClassSchema entityClass = new EntityClassSchema(entity.getName() + Constants.ENTITY_CLASS_SUFFIX);

            // create attributes for business entity fields
            for (EntityField field : entity.getFields()) {
                String[] result = extractType(field.getType());
                Attribute attribute = new Attribute(field.getName(), result[0], result[1]);
                entityClass.addAttribute(attribute);
            }

            entityClasses.add(entityClass);
            entities.remove(0);
        }

        return entityClasses;
    }

    private static String[] extractType(String type) {
        if (type.contains("array")) {
            int pos1 = type.indexOf("<");
            int pos2 = type.indexOf(">");
            return new String[]{type.substring(pos1 + 1, pos2), "array"};
        } else {
            return new String[]{type, "single"};
        }
    }
}
