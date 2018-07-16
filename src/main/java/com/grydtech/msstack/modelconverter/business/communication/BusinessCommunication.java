package com.grydtech.msstack.modelconverter.business.communication;

import com.grydtech.msstack.modelconverter.business.Field;
import com.grydtech.msstack.modelconverter.common.ModelComponent;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public abstract class BusinessCommunication extends ModelComponent {
    private String name;
    private final List<Field> fields = new ArrayList<>();

    public void addField(Field field) {
        this.fields.add(field);
    }

    public void addFieldCollection(Collection<Field> fields) {
        this.fields.addAll(fields);
    }
}
