package com.grydtech.msstack.modelconverter.engine;

import com.grydtech.msstack.modelconverter.business.BusinessContract;
import com.grydtech.msstack.modelconverter.business.BusinessEntity;
import com.grydtech.msstack.modelconverter.business.BusinessModel;
import com.grydtech.msstack.modelconverter.business.EntityField;
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
                microServiceModel = new MicroServiceModel(businessContract.getEntity().getName() + "_service");
                microServiceModel.setVersion(businessModel.getVersion());
                microServiceModel.setClassSchemas(this.extractEntities(businessContract.getEntity()));
                microServiceModelMap.put(businessContract.getEntityId(), microServiceModel);
            }

            Handler handler = new Handler(businessContract.getHandler().getName() + "_handler", businessContract.getHandler().getType());

            // get events from business contract and update class schemas and handlers according to that
            for (String event: businessContract.getEvents()) {
                // create class for each event
                ClassSchema classSchema = new ClassSchema(event + "_event");
                microServiceModel.addClassSchema(classSchema);

                Attribute attribute = new Attribute(event, classSchema.getName());
                attribute.isArray = false;

                // add event apply method to base entity (eg: void apply(OrderCreatedEvent event))
                ClassSchema baseEntity = microServiceModel.getClassSchemas().get(0);
                Method method = new Method("apply");
                method.addInput(attribute);

                // to wire event emitting inside handler
                handler.addEventGenerate(attribute);
                baseEntity.addMethod(method);
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

            ClassSchema classSchema = new ClassSchema(entity.getName() + "_entity");

            // create attributes for business entity fields
            for (EntityField field: entity.getFields()) {
                Attribute attribute = new Attribute(field.getName());
                String type;

                if (field.getType().contains("array")) {
                    attribute.isArray = true;
                    int pos1 = field.getType().indexOf("<");
                    int pos2 = field.getType().indexOf(">");
                    type = field.getType().substring(pos1 + 1, pos2);
                } else {
                    attribute.isArray = false;
                    type = field.getType();
                }

                BusinessEntity subEntity = field.getSubEntity();

                if (subEntity != null) {
                    entities.add(subEntity);
                    attribute.setType(subEntity.getName());
                } else {
                    attribute.setType(type);
                }

                classSchema.addAttribute(attribute);
            }

            entities.remove(0);
            classSchemas.add(classSchema);
        }

        return classSchemas;
    }
}
