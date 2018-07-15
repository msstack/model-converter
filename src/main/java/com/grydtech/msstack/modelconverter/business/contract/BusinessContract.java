package com.grydtech.msstack.modelconverter.business.contract;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.grydtech.msstack.modelconverter.business.communication.BusinessCommunication;
import com.grydtech.msstack.modelconverter.business.communication.BusinessEvent;
import com.grydtech.msstack.modelconverter.business.communication.BusinessRequest;
import com.grydtech.msstack.modelconverter.business.communication.BusinessResponse;
import com.grydtech.msstack.modelconverter.business.entity.BusinessEntity;
import com.grydtech.msstack.modelconverter.common.ModelComponent;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public final class BusinessContract extends ModelComponent {
    private String entityId;
    private BusinessContractHandler handler;
    private String requestId;
    private String responseId;
    private final List<String> eventIds = new ArrayList<>();
    @JsonIgnore
    private BusinessCommunication request; // when the handler type is event this will be BusinessEvent
    @JsonIgnore
    private BusinessCommunication response; // when the handler type is event this will be null
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
