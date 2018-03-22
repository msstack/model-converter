package com.grydtech.msstack.modelconverter;

import com.grydtech.msstack.modelconverter.business.BusinessModel;
import com.grydtech.msstack.modelconverter.engine.ModelReader;
import com.grydtech.msstack.modelconverter.engine.ModelReaderJackson;
import com.grydtech.msstack.modelconverter.engine.ModelWriter;
import com.grydtech.msstack.modelconverter.engine.ModelWriterJackson;
import com.grydtech.msstack.modelconverter.microservice.MicroServiceModel;

import java.io.File;
import java.io.IOException;

public class Main {
    private static ModelReader modelReader = new ModelReaderJackson();
    private static ModelWriter modelWriter = new ModelWriterJackson();

    public static void main(String[] args) {
        String method = args[0];
        String inputFilePath = args[1];
        String outputFilePath = args[2];
        File inputFile = new File(inputFilePath);
        File outputFile = new File(outputFilePath);
        try {
            outputFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // ToDo: file write is not implemented
        if (method.equals("b2m")){
            BusinessModel businessModel = modelReader.readBusinessModel(inputFile);
            modelWriter.writeModel(outputFile, businessModel);
        } else if (method.equals("m2b")) {
            MicroServiceModel microServiceModel = modelReader.readMicroServiceModel(inputFile);
            modelWriter.writeModel(outputFile, microServiceModel);
        }
    }
}
