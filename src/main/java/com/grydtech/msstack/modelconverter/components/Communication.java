package com.grydtech.msstack.modelconverter.components;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.grydtech.msstack.modelconverter.utils.Constants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public abstract class Communication extends ModelComponent {
    @JsonProperty("fields")
    private final List<Field> fields = new ArrayList<>();

    public void addField(Field field) {
        this.fields.add(field);
    }
}
