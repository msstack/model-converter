package com.grydtech.msstack.modelconverter.components;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public final class Entity extends ModelComponent {
    private List<Field> fields;

    @JsonIgnore
    private final List<Event> events = new ArrayList<>();

    public void addEvent(Event event) {
        this.events.add(event);
    }
}
