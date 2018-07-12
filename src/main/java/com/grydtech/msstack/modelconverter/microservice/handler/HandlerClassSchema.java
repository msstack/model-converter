package com.grydtech.msstack.modelconverter.microservice.handler;

import com.grydtech.msstack.modelconverter.microservice.ClassSchema;
import com.grydtech.msstack.modelconverter.microservice.communication.RequestClassSchema;
import com.grydtech.msstack.modelconverter.microservice.communication.ResponseClassSchema;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class HandlerClassSchema extends ClassSchema {

    private String type;
    private RequestClassSchema consume;
    private ResponseClassSchema produce;
    private final List<String> events = new ArrayList<>();

    public void addEvent(String event) {
        this.events.add(event);
    }

    public void addEventCollection(Collection<String> events) {
        this.events.addAll(events);
    }
}
