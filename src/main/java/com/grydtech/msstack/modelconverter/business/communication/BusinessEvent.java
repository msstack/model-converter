package com.grydtech.msstack.modelconverter.business.communication;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public final class BusinessEvent extends BusinessCommunication {
    private String entityId;
    @JsonIgnore
    private String eventGroup;
}
