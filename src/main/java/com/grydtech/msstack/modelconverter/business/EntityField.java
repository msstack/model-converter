package com.grydtech.msstack.modelconverter.business;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EntityField {
    private String name;
    private String type;

    // ToDo: Field validation not added (eg: isIdentifier, isUnique)

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
