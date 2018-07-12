package com.grydtech.msstack.modelconverter.business.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EntityField {
    private String name;
    private String type;

    // ToDo: Field validation not added (eg: isIdentifier, isUnique)
}
