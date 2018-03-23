package com.grydtech.msstack.modelconverter.microservice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class HandlerSchema {
    private String name;
    private String type;
    private Attribute consume;
    private Attribute produce;
    private List<Attribute> events;

    public HandlerSchema(String name, String type) {
        this.name = name;
        this.type = type;
        this.events = new ArrayList<Attribute>();
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

    public List<Attribute> getEvents() {
        return events;
    }

    public void addEvent(Attribute event) {
        this.events.add(event);
    }

    public void addEventCollection(Collection<Attribute> events) {
        this.events.addAll(events);
    }
}
