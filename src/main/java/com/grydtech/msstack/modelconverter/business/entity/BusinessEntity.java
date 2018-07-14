package com.grydtech.msstack.modelconverter.business.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.grydtech.msstack.modelconverter.business.Field;
import com.grydtech.msstack.modelconverter.business.communication.BusinessEvent;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
public class BusinessEntity {
    private String id;
    private String name;
    private final List<Field> fields = new ArrayList<>();
    @JsonIgnore
    private boolean mainEntity;
    @JsonIgnore
    private final List<BusinessEvent> events = new ArrayList<>();

    public void addField(Field field) {
        this.fields.add(field);
    }

    public void addFieldCollection(Collection<Field> fields) {
        this.fields.addAll(fields);
    }

    public void addEvent(BusinessEvent event) {
        this.events.add(event);
    }

    public void addEventCollection(Collection<BusinessEvent> events) {
        this.events.addAll(events);
    }
}
