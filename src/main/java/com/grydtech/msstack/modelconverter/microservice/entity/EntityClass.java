package com.grydtech.msstack.modelconverter.microservice.entity;

import com.grydtech.msstack.modelconverter.microservice.Attribute;
import com.grydtech.msstack.modelconverter.microservice.ClassSchema;
import com.grydtech.msstack.modelconverter.microservice.communication.EventClass;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class EntityClass extends ClassSchema {
    private boolean mainEntity;
    private final List<Attribute> attributes = new ArrayList<>();
    private final List<EventClass> eventClasses = new ArrayList<>();

    public void addAttribute(Attribute attribute) {
        this.attributes.add(attribute);
    }

    public void addAttributeCollection(Collection<Attribute> attributes) {
        this.attributes.addAll(attributes);
    }

    public void addEvent(EventClass event) {
        this.eventClasses.add(event);
    }

    public void addEventCollection(Collection<EventClass> events) {
        this.eventClasses.addAll(events);
    }

}
