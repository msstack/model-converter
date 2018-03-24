package com.grydtech.msstack.modelconverter.microservice;

import java.util.ArrayList;
import java.util.List;

public class RequestClassSchema extends CommunicationClassSchema {
    private String type;

    public RequestClassSchema(String name) {
        super(name);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
