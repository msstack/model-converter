package com.grydtech.msstack.modelconverter.business.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
public class BusinessEntity {
    private String id;
    private String name;
    private final List<EntityField> fields = new ArrayList<>();
    private final List<BusinessEntity> subEntities = new ArrayList<>();
    // ToDo: Read keys is not implemented

    public void addField(EntityField field) {
        this.fields.add(field);
    }

    public void addFieldCollection(Collection<EntityField> fields) {
        this.fields.addAll(fields);
    }

    public void addSubEntity(BusinessEntity subEntity) {
        this.subEntities.add(subEntity);
    }

    public void addSubEntityCollection(Collection<BusinessEntity> subEntities) {
        this.subEntities.addAll(subEntities);
    }
}
