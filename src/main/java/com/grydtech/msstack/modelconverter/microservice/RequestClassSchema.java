package com.grydtech.msstack.modelconverter.microservice;

import java.util.ArrayList;
import java.util.List;

public class RequestClassSchema extends ClassSchema {

    private String type;
    private List<Attribute> attributes;

    public RequestClassSchema(String name) {
        super(name);
        this.attributes = new ArrayList<>();
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

}
