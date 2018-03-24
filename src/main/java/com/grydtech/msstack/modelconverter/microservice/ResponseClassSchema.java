package com.grydtech.msstack.modelconverter.microservice;

import java.util.ArrayList;
import java.util.List;

public class ResponseClassSchema extends ClassSchema {

    private List<Attribute> attributes;

    public ResponseClassSchema(String name) {
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
