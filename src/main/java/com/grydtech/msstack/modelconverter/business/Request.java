package com.grydtech.msstack.modelconverter.business;

import java.util.ArrayList;
import java.util.List;

public class Request {

    private String id;
    private String name;
    private String type;
    private List<EntityField> fields;

    public Request() {
        this.fields = new ArrayList<>();
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
