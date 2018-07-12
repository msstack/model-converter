package com.grydtech.msstack.modelconverter.microservice.entity;

import com.grydtech.msstack.modelconverter.microservice.ClassSchema;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class EntityClassSchema extends ClassSchema {
    private final List<Attribute> attributes = new ArrayList<>();
    private final List<String> events = new ArrayList<>();

    public void addAttribute(Attribute attribute) {
        this.attributes.add(attribute);
    }

    public void addAttributeCollection(Collection<Attribute> attributes) {
        this.attributes.addAll(attributes);
    }

    public void addEvent(String event) {
        this.events.add(event);
    }

    public void addEventCollection(Collection<String> events) {
        this.events.addAll(events);
    }

}
