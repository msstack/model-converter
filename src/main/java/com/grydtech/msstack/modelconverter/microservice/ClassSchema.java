package com.grydtech.msstack.modelconverter.microservice;

import java.util.ArrayList;
import java.util.List;

public class ClassSchema {
    private String name;
    private List<Attribute> attributes;
    private List<Method> methods;

    public ClassSchema(String name) {
        this.name = name;
        this.attributes = new ArrayList<Attribute>();
        this.methods = new ArrayList<Method>();
    }

    public String getName() {
        return name;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public List<Method> getMethods() {
        return methods;
    }

    public void addAttribute(Attribute attribute) {
        this.attributes.add(attribute);
    }

    public void addMethod(Method method) {
        this.methods.add(method);
    }
}
