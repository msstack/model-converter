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
        Map<String, MicroServiceModel> microServiceModelMap = new HashMap<String, MicroServiceModel>();

        for (BusinessContract businessContract : businessModel.getContracts()) {
            MicroServiceModel microServiceModel;

            // generate micro service models according to entities (one entity per one micro service)
            if (microServiceModelMap.containsKey(businessContract.getEntityId())) {
                microServiceModel = microServiceModelMap.get(businessContract.getEntityId());
            } else {
                microServiceModel = new MicroServiceModel(businessContract.getEntity().getName() + Constants.SERVICE_SUFFIX);
                microServiceModel.setVersion(businessModel.getVersion());
                microServiceModel.setEntityClasses(this.extractEntities(businessContract.getEntity()));
                microServiceModelMap.put(businessContract.getEntityId(), microServiceModel);
            }

            Handler handler = new Handler(businessContract.getHandler().getName() + Constants.HANDLER_CLASS_SUFFIX, businessContract.getHandler().getType());

            // get events from business contract and update class schemas and handlers according to that
            for (String event: businessContract.getEvents()) {
                // create class for each event
                ClassSchema eventClass = new ClassSchema(event + Constants.EVENT_CLASS_SUFFIX);
                microServiceModel.addEventClass(eventClass);

                // add event apply method to base entity (eg: void apply(OrderCreatedEvent event))
                ClassSchema baseEntityClass = microServiceModel.getEntityClasses().get(0);
                Attribute attribute = new Attribute(event, eventClass.getName());
                Method method = new Method("apply");
                method.addInput(attribute);
                baseEntityClass.addMethod(method);

                // to wire event emitting inside handler
                handler.addEventGenerate(attribute);
            }

            microServiceModel.addHandler(handler);
        }

        return new ArrayList<MicroServiceModel>(microServiceModelMap.values());
    }

    // recursively search for entities (sub entities) and create class schemas
    private List<ClassSchema> extractEntities(BusinessEntity baseEntity) {
        // ToDo: Validations not implemented
        List<ClassSchema> classSchemas = new ArrayList<ClassSchema>();
        List<BusinessEntity> entities = new ArrayList<BusinessEntity>();
        entities.add(baseEntity);

        while (!entities.isEmpty()) {
            BusinessEntity entity = entities.get(0);
            entities.addAll(entity.getSubEntities());

            ClassSchema classSchema = new ClassSchema(entity.getName() + Constants.ENTITY_CLASS_SUFFIX);

            // create attributes for business entity fields
            for (EntityField field: entity.getFields()) {
                Attribute attribute = new Attribute(field.getName(), field.getType());
                classSchema.addAttribute(attribute);
            }

            classSchemas.add(classSchema);
            entities.remove(0);
        }

        return classSchemas;
    }
}
