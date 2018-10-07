package com.grydtech.msstack.modelconverter.components;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public final class Event extends Communication {
    private final List<Field> fields = new ArrayList<>();
    @JsonProperty("entity")
    private String entityRef;

    public void addField(Field field) {
        this.fields.add(field);
    }
}
