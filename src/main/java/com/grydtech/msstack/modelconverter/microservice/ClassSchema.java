package com.grydtech.msstack.modelconverter.microservice;

import com.grydtech.msstack.modelconverter.common.ModelComponent;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class ClassSchema extends ModelComponent {
    private String name;
}
