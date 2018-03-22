package com.grydtech.msstack.modelconverter.business;

import java.util.List;

public class BusinessContract {
    private String id;
    private String name;
    private String entityId;
    private ContractHandler handler;
    private List<String> events;
    private BusinessEntity entity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public ContractHandler getHandler() {
        return handler;
    }

    public void setHandler(ContractHandler handler) {
        this.handler = handler;
    }

    public List<String> getEvents() {
        return events;
    }

    public void setEvents(List<String> events) {
        this.events = events;
    }

    public BusinessEntity getEntity() {
        return entity;
    }

    public void setEntity(BusinessEntity entity) {
        this.entity = entity;
    }
}
