package com.grydtech.msstack.modelconverter.business.contract;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.grydtech.msstack.modelconverter.business.communication.BusinessEvent;
import com.grydtech.msstack.modelconverter.business.communication.BusinessRequest;
import com.grydtech.msstack.modelconverter.business.communication.BusinessResponse;
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
    private BusinessContractHandler handler;
    private String requestId;
    private String responseId;
    private final List<String> eventIds = new ArrayList<>();
    @JsonIgnore
    private BusinessRequest request;
    @JsonIgnore
    private BusinessResponse response;
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
