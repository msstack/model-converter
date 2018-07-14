package com.grydtech.msstack.modelconverter.microservice;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public final class Attribute {
    private String name;
    private String type;
    private boolean entity;
    private boolean array;
}
