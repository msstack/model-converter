package com.grydtech.msstack.modelconverter.business;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ContractCommunication {
    private String id;
    private String name;
    private List<EntityField> fields;

    public ContractCommunication() {
        this.fields = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EntityField> getFields() {
        return fields;
    }

    public void addField(EntityField field) {
        this.fields.add(field);
    }

    public void addFieldCollection(Collection<EntityField> fields) {
        this.fields.addAll(fields);
    }
}
