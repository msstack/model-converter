package com.grydtech.msstack.modelconverter.business.contract;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.grydtech.msstack.modelconverter.business.entity.BusinessEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
public class BusinessContract {

    private String id;
    private String entityId;
    private ContractHandler handler;
    private String requestId;
    private String responseId;
    private final List<String> event_ids = new ArrayList<>();
    @JsonIgnore
    private ContractRequest request;
    @JsonIgnore
    private ContractResponse response;
    @JsonIgnore
    private BusinessEntity entity;
    @JsonIgnore
    private final List<BusinessEvent> events = new ArrayList<>();

    public void addEvent(BusinessEvent event) {
        this.events.add(event);
    }

    public void addEventCollection(Collection<BusinessEvent> events) {
        this.events.addAll(events);
    }
}
