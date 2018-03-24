package com.grydtech.msstack.modelconverter.microservice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CommunicationClassSchema extends ClassSchema {
    private List<Attribute> attributes;

    public CommunicationClassSchema(String name) {
        super(name);
        this.attributes = new ArrayList<>();
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void addAttribute(Attribute attribute) {
        this.attributes.add(attribute);
    }

    public void addAttributeCollection(Collection<Attribute> attributes) {
        this.attributes.addAll(attributes);
    }
}
