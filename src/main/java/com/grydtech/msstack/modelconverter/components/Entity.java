package com.grydtech.msstack.modelconverter.components;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public final class Entity extends ModelComponent {
    @JsonProperty("field")
    private List<Field> fields;

    @JsonIgnore
    private final List<Event> events = new ArrayList<>();

    public void addEvent(Event event) {
        this.events.add(event);
    }
}
