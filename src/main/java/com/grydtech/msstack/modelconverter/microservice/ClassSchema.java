package com.grydtech.msstack.modelconverter.microservice;

import com.grydtech.msstack.modelconverter.common.ModelComponent;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public abstract class ClassSchema extends ModelComponent {
    private String name;
}
