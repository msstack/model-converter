package com.grydtech.msstack.modelconverter;

import com.grydtech.msstack.modelconverter.business.BusinessModel;
import com.grydtech.msstack.modelconverter.engine.*;
import com.grydtech.msstack.modelconverter.microservice.MicroServiceModel;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    private static ModelReader modelReader = new ModelReaderJackson();
    private static ModelWriter modelWriter = new ModelWriterJackson();
    private static ModelConverter modelConverter = new ModelConverterEntityBased();

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
            List<MicroServiceModel> microServiceModels = modelConverter.convertToMicroServiceModel(businessModel);
            modelWriter.writeModel(outputFile, microServiceModels.get(0));
        } else if (method.equals("m2b")) {
            MicroServiceModel microServiceModel = modelReader.readMicroServiceModel(inputFile);
            modelWriter.writeModel(outputFile, microServiceModel);
        }
    }
}
