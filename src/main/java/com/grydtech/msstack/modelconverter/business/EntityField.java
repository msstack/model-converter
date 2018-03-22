package com.grydtech.msstack.modelconverter.business;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EntityField {
    private String name;
    private String type;
    private BusinessEntity subEntity;
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

    public BusinessEntity getSubEntity() {
        return subEntity;
    }

    public void setSubEntity(BusinessEntity subEntity) {
        this.subEntity = subEntity;
    }
}
