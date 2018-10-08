package com.grydtech.msstack.modelconverter.components;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.grydtech.msstack.modelconverter.utils.Constants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public final class Contract extends ModelComponent {
    @JsonProperty("type")
    private Constants.HANDLER_TYPE type;
    @JsonProperty("entity")
    private String entityRef;
    @JsonProperty("consumes")
    private String consumesRef;
    @JsonIgnore
    private final List<String> producesOnSuccessRefs = new ArrayList<>();
    @JsonIgnore
    private final List<String> producesOnErrorRefs = new ArrayList<>();

    @JsonIgnore
    private Entity entity;
    @JsonIgnore
    private Communication consumes;
    @JsonIgnore
    private final List<Communication> producesOnSuccess = new ArrayList<>();
    @JsonIgnore
    private final List<Communication> producesOnError = new ArrayList<>();

    @JsonProperty("produces")
    private void unpackProduces(Map<String, List<String>> produces) {
        producesOnSuccessRefs.addAll(produces.get("on_success"));
        producesOnErrorRefs.addAll(produces.get("on_failure"));
    }

    public void addProducesOnSuccess(Communication communication) {
        this.producesOnSuccess.add(communication);
    }

    public void addProducesOnError(Communication communication) {
        this.producesOnError.add(communication);
    }
}