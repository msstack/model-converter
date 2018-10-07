package com.grydtech.msstack.modelconverter.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class Model {
    private String schemaVersion;
}
