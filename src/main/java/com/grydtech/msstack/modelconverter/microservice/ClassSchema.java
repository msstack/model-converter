package com.grydtech.msstack.modelconverter.microservice;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class ClassSchema {
    private String id;
    private String name;
}
