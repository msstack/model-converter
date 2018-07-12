package com.grydtech.msstack.modelconverter.microservice;

import com.grydtech.msstack.modelconverter.common.Model;
import com.grydtech.msstack.modelconverter.microservice.entity.EntityClassSchema;
import com.grydtech.msstack.modelconverter.microservice.communication.EventClassSchema;
import com.grydtech.msstack.modelconverter.microservice.handler.HandlerClassSchema;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class MicroServiceModel extends Model {
    private String serviceName;
    private final List<EntityClassSchema> entityClasses = new ArrayList<>();
    private final List<EventClassSchema> eventClasses = new ArrayList<>();
    private final List<HandlerClassSchema> handlers = new ArrayList<>();

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
