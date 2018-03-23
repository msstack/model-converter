package com.grydtech.msstack.modelconverter.microservice;

import com.grydtech.msstack.modelconverter.common.Model;

import java.util.ArrayList;
import java.util.List;

public class MicroServiceModel extends Model {
    private String serviceName;
    private List<ClassSchema> entityClasses;
    private List<ClassSchema> eventClasses;
    private List<Handler> handlers;

    public MicroServiceModel(String serviceName) {
        this.serviceName = serviceName;
        this.entityClasses = new ArrayList<ClassSchema>();
        this.eventClasses = new ArrayList<ClassSchema>();
        this.handlers = new ArrayList<Handler>();
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public List<ClassSchema> getEntityClasses() {
        return entityClasses;
    }

    public void setEntityClasses(List<ClassSchema> entityClasses) {
        this.entityClasses = entityClasses;
    }

    public List<ClassSchema> getEventClasses() {
        return eventClasses;
    }

    public void setEventClasses(List<ClassSchema> eventClasses) {
        this.eventClasses = eventClasses;
    }

    public List<Handler> getHandlers() {
        return handlers;
    }

    public void addEntityClass(ClassSchema entityClass) {
        this.entityClasses.add(entityClass);
    }

    public void addEventClass(ClassSchema eventClass) {
        this.eventClasses.add(eventClass);
    }

    public void addHandler(Handler handler) {
        this.handlers.add(handler);
    }

}
