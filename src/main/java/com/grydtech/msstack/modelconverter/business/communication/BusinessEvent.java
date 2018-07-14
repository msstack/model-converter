package com.grydtech.msstack.modelconverter.business.communication;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.grydtech.msstack.modelconverter.business.entity.BusinessEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class BusinessEvent extends BusinessCommunication {
    private String entityId;
}
