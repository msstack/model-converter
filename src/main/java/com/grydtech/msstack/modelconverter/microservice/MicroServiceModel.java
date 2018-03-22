package com.grydtech.msstack.modelconverter.microservice;

import com.grydtech.msstack.modelconverter.common.Model;

import java.util.ArrayList;
import java.util.List;

public class MicroServiceModel extends Model {
    private String serviceName;
    private List<ClassSchema> classSchemas;
    private List<Handler> handlers;

    public MicroServiceModel(String serviceName) {
        this.serviceName = serviceName;
        this.classSchemas = new ArrayList<ClassSchema>();
        this.handlers = new ArrayList<Handler>();
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public List<ClassSchema> getClassSchemas() {
        return classSchemas;
    }

    public List<Handler> getHandlers() {
        return handlers;
    }

    public void setClassSchemas(List<ClassSchema> classSchemas) {
        this.classSchemas = classSchemas;
    }

    public void addClassSchema (ClassSchema classSchema) {
        this.classSchemas.add(classSchema);
    }

    public void addHandler(Handler handler) {
        this.handlers.add(handler);
    }

}
