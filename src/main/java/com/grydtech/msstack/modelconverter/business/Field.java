package com.grydtech.msstack.modelconverter.business;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.grydtech.msstack.modelconverter.business.entity.BusinessEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public final class Field {
    private String name;
    private String type;
    private boolean array;
    @JsonIgnore
    private BusinessEntity entity;

    // ToDo: Field validation not added (eg: isIdentifier, isUnique)
}
