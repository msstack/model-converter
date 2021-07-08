package com.grydtech.msstack.modelconverter.components;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public final class Event extends Communication {
    @JsonProperty("entity")
    private String entityRef;
}
