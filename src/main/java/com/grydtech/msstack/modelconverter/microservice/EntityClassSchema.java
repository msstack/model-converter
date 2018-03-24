package com.grydtech.msstack.modelconverter.microservice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EntityClassSchema extends ClassSchema {
    private List<Attribute> attributes;
    private List<String> events;

    public EntityClassSchema(String name) {
        super(name);
        this.attributes = new ArrayList<>();
        this.events = new ArrayList<>();
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

    public List<String> getEvents() {
        return events;
    }

    public void addEvent(String event) {
        this.events.add(event);
    }

    public void addEventCollection(Collection<String> events) {
        this.events.addAll(events);
    }

}
