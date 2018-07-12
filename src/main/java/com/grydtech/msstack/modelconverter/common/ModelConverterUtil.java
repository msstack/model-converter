package com.grydtech.msstack.modelconverter.common;

import com.grydtech.msstack.modelconverter.business.entity.BusinessEntity;
import com.grydtech.msstack.modelconverter.business.entity.EntityField;
import com.grydtech.msstack.modelconverter.microservice.entity.Attribute;
import com.grydtech.msstack.modelconverter.microservice.entity.EntityClassSchema;

import java.util.ArrayList;
import java.util.List;

public final class ModelConverterUtil {

    private ModelConverterUtil() {
    }

    // recursively search for entities (sub entities) and create class schemas
    public static List<EntityClassSchema> extractEntities(BusinessEntity baseEntity) {
        // ToDo: Validations not implemented
        List<EntityClassSchema> entityClasses = new ArrayList<>();
        List<BusinessEntity> entities = new ArrayList<>();
        entities.add(baseEntity);

        while (!entities.isEmpty()) {
            BusinessEntity entity = entities.get(0);
            entities.addAll(entity.getSubEntities());

            EntityClassSchema entityClass = new EntityClassSchema();
            entityClass.setName(entity.getName() + Constants.ENTITY_CLASS_SUFFIX);

            // create attributes for business entity fields
            entity.getFields().forEach(field -> {
                String[] result = extractType(field.getType());

                if (!Constants.BASE_TYPES.contains(result[0])) {
                    result[0] += Constants.ENTITY_CLASS_SUFFIX;
                }

                Attribute attribute = new Attribute();
                attribute.setName(field.getName());
                attribute.setName(result[0]);
                attribute.setName(result[1]);
                entityClass.addAttribute(attribute);
            });

            entityClasses.add(entityClass);
            entities.remove(0);
        }

        return entityClasses;
    }

    public static Attribute extractAttribute(EntityField entityField, String multiplicity) {
        Attribute attribute = new Attribute();
        attribute.setName(entityField.getName());
        attribute.setType(entityField.getType());
        attribute.setMultiplicity(multiplicity);

        return attribute;
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
