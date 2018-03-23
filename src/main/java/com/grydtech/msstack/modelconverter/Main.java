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
        File inputFile = new File(inputFilePath);
        String outputPath = null;
        try {
            outputPath = new File(".").getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // ToDo: file write is not implemented
        if ("b2m".equals(method)){
            BusinessModel businessModel = modelReader.readBusinessModel(inputFile);
            List<MicroServiceModel> microServiceModels = modelConverter.convertToMicroServiceModel(businessModel);
            for (MicroServiceModel microServiceModel: microServiceModels) {
                File outputFile = new File(outputPath + File.separator + microServiceModel.getServiceName() + ".json");
                try {
                    outputFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                modelWriter.writeModel(outputFile, microServiceModel);
            }
        } else if ("m2b".equals(method)) {
            MicroServiceModel microServiceModel = modelReader.readMicroServiceModel(inputFile);
        }
    }
}
