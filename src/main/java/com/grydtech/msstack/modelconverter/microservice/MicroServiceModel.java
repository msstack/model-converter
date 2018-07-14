package com.grydtech.msstack.modelconverter.microservice;

import com.grydtech.msstack.modelconverter.common.Model;
import com.grydtech.msstack.modelconverter.microservice.communication.RequestClass;
import com.grydtech.msstack.modelconverter.microservice.communication.ResponseClass;
import com.grydtech.msstack.modelconverter.microservice.entity.EntityClass;
import com.grydtech.msstack.modelconverter.microservice.communication.EventClass;
import com.grydtech.msstack.modelconverter.microservice.handler.HandlerClass;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class MicroServiceModel extends Model {
    private String serviceName;
    private final List<EntityClass> entityClasses = new ArrayList<>();
    private final List<RequestClass> requestClasses = new ArrayList<>();
    private final List<ResponseClass> responseClasses = new ArrayList<>();
    private final List<EventClass> eventClasses = new ArrayList<>();
    private final List<HandlerClass> handlerClasses = new ArrayList<>();

    public void addEntityClass(EntityClass entityClass) {
        this.entityClasses.add(entityClass);
    }

    public void addEntityClassCollection(Collection<EntityClass> entityClasses) {
        this.entityClasses.addAll(entityClasses);
    }

    public void addRequestClass(RequestClass requestClass) {
        this.requestClasses.add(requestClass);
    }

    public void addRequestClassCollection(Collection<RequestClass> requestClasses) {
        this.requestClasses.addAll(requestClasses);
    }

    public void addResponseClass(ResponseClass responseClass) {
        this.responseClasses.add(responseClass);
    }

    public void addResponseClassCollection(Collection<ResponseClass> responseClasses) {
        this.responseClasses.addAll(responseClasses);
    }

    public void addEventClass(EventClass eventClass) {
        this.eventClasses.add(eventClass);
    }

    public void addEventClassCollection(Collection<EventClass> eventClasses) {
        this.eventClasses.addAll(eventClasses);
    }

    public void addHandlerClass(HandlerClass handler) {
        this.handlerClasses.add(handler);
    }

    public void addHandlerClassCollection(Collection<HandlerClass> handlers) {
        this.handlerClasses.addAll(handlers);
    }
}
