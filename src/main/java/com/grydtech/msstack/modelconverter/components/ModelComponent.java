package com.grydtech.msstack.modelconverter.components;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class ModelComponent {
    private String name;
    private String description;
}
