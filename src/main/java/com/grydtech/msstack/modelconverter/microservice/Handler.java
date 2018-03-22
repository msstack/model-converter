package com.grydtech.msstack.modelconverter.microservice;

import java.util.ArrayList;
import java.util.List;

public class Handler {
    private String name;
    private String type;
    private Attribute consume;
    private Attribute produce;
    private List<Attribute> eventsGenerate;

    public Handler(String name, String type) {
        this.name = name;
        this.type = type;
        this.eventsGenerate = new ArrayList<Attribute>();
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

    public List<Attribute> getEventsGenerate() {
        return eventsGenerate;
    }

    public void addEventGenerate(Attribute event) {
        this.eventsGenerate.add(event);
    }
}
