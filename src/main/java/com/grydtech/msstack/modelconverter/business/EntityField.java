package com.grydtech.msstack.modelconverter.business;

public class EntityField {
    private String name;
    private String type;
    private EntityField subEntity;
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

    public EntityField getSubEntity() {
        return subEntity;
    }

    public void setSubEntity(EntityField subEntity) {
        this.subEntity = subEntity;
    }
}
