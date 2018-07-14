package com.grydtech.msstack.modelconverter;

import com.grydtech.msstack.modelconverter.business.BusinessModel;
import com.grydtech.msstack.modelconverter.services.*;
import com.grydtech.msstack.modelconverter.microservice.MicroServiceModel;
import com.grydtech.msstack.modelconverter.services.impl.DefaultModelConverter;
import com.grydtech.msstack.modelconverter.services.impl.DefaultModelReader;
import com.grydtech.msstack.modelconverter.services.impl.DefaultModelWriter;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    private static ModelReader modelReader = new DefaultModelReader();
    private static ModelWriter modelWriter = new DefaultModelWriter();
    private static ModelConverter modelConverter = new DefaultModelConverter();

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
        if ("b2m".equals(method)) {
            BusinessModel businessModel = modelReader.readBusinessModel(inputFile);
            List<MicroServiceModel> microServiceModels = modelConverter.convertToMicroServiceModel(businessModel);
            for (MicroServiceModel microServiceModel : microServiceModels) {
                File outputFile = new File(outputPath + File.separator + microServiceModel.getServiceName() + ".json");
                try {
                    outputFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                modelWriter.writeModel(outputFile, microServiceModel);
            }
        } else if ("m2b".equals(method)) {
            throw new UnsupportedOperationException();
        }
    }
}
