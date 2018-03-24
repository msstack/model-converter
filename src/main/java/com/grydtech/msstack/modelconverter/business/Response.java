package com.grydtech.msstack.modelconverter.business;

import java.util.ArrayList;
import java.util.List;

public class Response {

    private String id;
    private String name;
    private List<EntityField> fields;

    public Response() {
        this.fields = this.fields = new ArrayList<>();
    }

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

    public void setField(EntityField field) {
        this.fields.add(field);
    }

}
