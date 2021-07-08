package com.grydtech.msstack.modelconverter.services.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.grydtech.msstack.modelconverter.models.Model;
import com.grydtech.msstack.modelconverter.services.ModelWriter;

import java.io.File;
import java.io.IOException;

public class DefaultModelWriter implements ModelWriter {
    private static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public void writeModel(File file, Model model) {
        try {
            objectMapper.writeValue(file, model);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
