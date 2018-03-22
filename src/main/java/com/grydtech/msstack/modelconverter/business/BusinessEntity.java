package com.grydtech.msstack.modelconverter.business;

import java.util.List;

public class BusinessEntity {
    private String id;
    private String name;
    private List<EntityField> fields;
    // ToDo: Read keys is not implemented

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EntityField> getFields() {
        return fields;
    }

    public void setFields(List<EntityField> fields) {
        this.fields = fields;
    }
}
