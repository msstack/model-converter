package com.grydtech.msstack.modelconverter.microservice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class HandlerClassSchema extends ClassSchema {
    private String type;
    private Attribute consume;
    private Attribute produce;
    private List<String> events;

    public HandlerClassSchema(String name, String type) {
        super(name);
        this.type = type;
        this.events = new ArrayList<>();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Attribute getConsume() {
        return consume;
    }

    public void setConsume(Attribute consume) {
        this.consume = consume;
    }

    public Attribute getProduce() {
        return produce;
    }

    public void setProduce(Attribute produce) {
        this.produce = produce;
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
