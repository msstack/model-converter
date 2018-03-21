package com.grydtech.msstack.modelconverter.business;

import java.lang.reflect.Field;
import java.util.List;

public class BusinessEntity {
    private String _id;
    private String name;
    private List<Field> keys;
    private List<EntityField> fields;
}
