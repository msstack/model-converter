package com.grydtech.msstack.modelconverter.microservice;

import java.util.ArrayList;
import java.util.List;

public class Method {
    private String name;
    private List<Attribute> inputs;
    private Attribute output;

    public Method(String name) {
        this.name = name;
        this.inputs = new ArrayList<Attribute>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Attribute getOutput() {
        return output;
    }

    public void setOutput(Attribute output) {
        this.output = output;
    }

    public List<Attribute> getInputs() {
        return inputs;
    }

    public void addInput(Attribute attribute) {
        this.inputs.add(attribute);
    }
}
