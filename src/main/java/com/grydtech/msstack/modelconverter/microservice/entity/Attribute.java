package com.grydtech.msstack.modelconverter.microservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public final class Attribute {
    private String name;
    private String type;
    private String multiplicity;
}
