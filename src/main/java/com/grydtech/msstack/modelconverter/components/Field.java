package com.grydtech.msstack.modelconverter.components;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.grydtech.msstack.modelconverter.utils.Constants;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public final class Field {
    private String name;
    @JsonProperty("type")
    private String typeRef;
    private List<Constants.CONSTRAINT> constraints;

    @JsonIgnore
    private Constants.FIELD_TYPE type;
    @JsonIgnore
    private Constants.FIELD_MULTIPLICITY multiplicity;
    @JsonIgnore
    private ModelComponent component;
}
