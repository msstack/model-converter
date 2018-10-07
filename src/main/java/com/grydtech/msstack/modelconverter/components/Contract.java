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
    private List<String> producesOnSuccessRefs;
    @JsonIgnore
    private List<String> producesOnErrorRefs;

    @JsonIgnore
    private Entity entity;
    @JsonIgnore
    private Communication consumes;
    @JsonIgnore
    private final List<Communication> producesOnSuccess = new ArrayList<>();
    @JsonIgnore
    private final List<Communication> producesOnError = new ArrayList<>();

    @JsonProperty("produces")
    private Map<String, Object> packProduces() {
        Map<String, Object> produces = new HashMap<>();
        produces.put("onSuccess", this.producesOnSuccessRefs);
        produces.put("onError", this.producesOnErrorRefs);
        return produces;
    }

    @JsonProperty("produces")
    private void unpackProduces(Map<String, Object> produces) {
        producesOnSuccessRefs = Arrays.asList((String[]) produces.get("onSuccess"));
        producesOnErrorRefs = Arrays.asList((String[]) produces.get("onError"));
    }

    public void addProducesOnSuccess(Communication communication) {
        this.producesOnSuccess.add(communication);
    }

    public void addProducesOnError(Communication communication) {
        this.producesOnError.add(communication);
    }
}