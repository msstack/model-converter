package com.grydtech.msstack.modelconverter.microservice;

import com.grydtech.msstack.modelconverter.common.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MicroServiceModel extends Model {
    private String serviceName;
    private List<EntityClassSchema> entityClasses;
    private List<EventClassSchema> eventClasses;
    private List<HandlerClassSchema> handlers;

    public MicroServiceModel(String serviceName) {
        this.serviceName = serviceName;
        this.entityClasses = new ArrayList<>();
        this.eventClasses = new ArrayList<>();
        this.handlers = new ArrayList<>();
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public List<EntityClassSchema> getEntityClasses() {
        return entityClasses;
    }

    public List<EventClassSchema> getEventClasses() {
        return eventClasses;
    }

    public List<HandlerClassSchema> getHandlers() {
        return handlers;
    }

    public void addEntityClass(EntityClassSchema entityClass) {
        this.entityClasses.add(entityClass);
    }

    public void addEntityClassCollection(Collection<EntityClassSchema> entityClasses) {
        this.entityClasses.addAll(entityClasses);
    }

    public void addEventClass(EventClassSchema eventClass) {
        this.eventClasses.add(eventClass);
    }

    public void addEventClassCollection(Collection<EventClassSchema> eventClasses) {
        this.eventClasses.addAll(eventClasses);
    }

    public void addHandler(HandlerClassSchema handler) {
        this.handlers.add(handler);
    }

    public void addHandlerCollection(Collection<HandlerClassSchema> handlers) {
        this.handlers.addAll(handlers);
    }
}
