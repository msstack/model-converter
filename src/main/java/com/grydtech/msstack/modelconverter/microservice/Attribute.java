package com.grydtech.msstack.modelconverter.microservice;

public class Attribute {
    private String name;
    private String type;
    private String multiplicity;

    public Attribute(String name, String type, String multiplicity) {
        this.name = name;
        this.type = type;
        this.multiplicity = multiplicity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMultiplicity() {
        return multiplicity;
    }

    public void setMultiplicity(String multiplicity) {
        this.multiplicity = multiplicity;
    }
}
