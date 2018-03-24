package com.grydtech.msstack.modelconverter.microservice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EventClassSchema extends ClassSchema {
    private List<Attribute> attributes;

    public EventClassSchema(String name) {
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
