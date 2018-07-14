package com.grydtech.msstack.modelconverter.business.communication;

import com.grydtech.msstack.modelconverter.business.Field;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
public abstract class BusinessCommunication {
    private String id;
    private String name;
    private final List<Field> fields = new ArrayList<>();

    public void addField(Field field) {
        this.fields.add(field);
    }

    public void addFieldCollection(Collection<Field> fields) {
        this.fields.addAll(fields);
    }
}
