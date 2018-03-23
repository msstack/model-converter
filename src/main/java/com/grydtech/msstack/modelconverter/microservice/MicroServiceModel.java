package com.grydtech.msstack.modelconverter.microservice;

import com.grydtech.msstack.modelconverter.common.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MicroServiceModel extends Model {
    private String serviceName;
    private List<ClassSchema> entityClasses;
    private List<ClassSchema> eventClasses;
    private List<HandlerSchema> handlers;

    public MicroServiceModel(String serviceName) {
        this.serviceName = serviceName;
        this.entityClasses = new ArrayList<ClassSchema>();
        this.eventClasses = new ArrayList<ClassSchema>();
        this.handlers = new ArrayList<HandlerSchema>();
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

    public List<ClassSchema> getEventClasses() {
        return eventClasses;
    }

    public List<HandlerSchema> getHandlers() {
        return handlers;
    }

    public void addEntityClass(ClassSchema entityClass) {
        this.entityClasses.add(entityClass);
    }

    public void addEntityClassCollection(Collection<ClassSchema> entityClasses) {
        this.entityClasses.addAll(entityClasses);
    }

    public void addEventClass(ClassSchema eventClass) {
        this.eventClasses.add(eventClass);
    }

    public void addEventClassCollection(Collection<ClassSchema> eventClasses) {
        this.eventClasses.addAll(eventClasses);
    }

    public void addHandler(HandlerSchema handler) {
        this.handlers.add(handler);
    }

    public void addHandlerCollection(Collection<HandlerSchema> handlers) {
        this.handlers.addAll(handlers);
    }
}
