package com.grydtech.msstack.modelconverter.business.contract;

import com.grydtech.msstack.modelconverter.business.entity.EntityField;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
public abstract class ContractCommunication {
    private String id;
    private String name;
    private final List<EntityField> fields = new ArrayList<>();

    public void addField(EntityField field) {
        this.fields.add(field);
    }

    public void addFieldCollection(Collection<EntityField> fields) {
        this.fields.addAll(fields);
    }
}
