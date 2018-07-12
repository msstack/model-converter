package com.grydtech.msstack.modelconverter.business.contract;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.grydtech.msstack.modelconverter.business.entity.BusinessEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BusinessEvent {

    private String id;
    private String name;
    private String entityId;
    @JsonIgnore
    private BusinessEntity entity;
}
