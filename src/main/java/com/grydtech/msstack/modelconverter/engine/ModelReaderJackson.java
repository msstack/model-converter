package com.grydtech.msstack.modelconverter.engine;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.grydtech.msstack.modelconverter.business.BusinessModel;
import com.grydtech.msstack.modelconverter.microservice.MicroServiceModel;

import java.io.File;
import java.io.IOException;

public class ModelReaderJackson implements ModelReader {
    private static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
    }

    public BusinessModel readBusinessModel(File file) {
        BusinessModel businessModel = null;
        try {
            businessModel = objectMapper.readValue(file, BusinessModel.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return businessModel;
    }

    public MicroServiceModel readMicroServiceModel(File file) {
        MicroServiceModel microServiceModel = null;
        try {
            microServiceModel = objectMapper.readValue(file, MicroServiceModel.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return microServiceModel;
    }
}
