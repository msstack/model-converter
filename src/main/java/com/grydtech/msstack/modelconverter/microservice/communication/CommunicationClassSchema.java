package com.grydtech.msstack.modelconverter.microservice.communication;

import com.grydtech.msstack.modelconverter.microservice.entity.Attribute;
import com.grydtech.msstack.modelconverter.microservice.ClassSchema;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public abstract class CommunicationClassSchema extends ClassSchema {
    private final List<Attribute> attributes = new ArrayList<>();

    public void addAttribute(Attribute attribute) {
        this.attributes.add(attribute);
    }

    public void addAttributeCollection(Collection<Attribute> attributes) {
        this.attributes.addAll(attributes);
    }
}
