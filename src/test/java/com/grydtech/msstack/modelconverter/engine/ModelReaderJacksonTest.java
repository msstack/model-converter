package com.grydtech.msstack.modelconverter.engine;

import com.grydtech.msstack.modelconverter.business.BusinessModel;
import com.grydtech.msstack.modelconverter.microservice.MicroServiceModel;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.util.List;

public class ModelReaderJacksonTest {
    @Test
    public void readBusinessModelTest() {
        File jsonFile = null;
        try {
            jsonFile = new File(this.getClass().getResource("/sample-business-model.json").toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        BusinessModel businessModel = new ModelReaderJackson().readBusinessModel(jsonFile);
        List<MicroServiceModel> microServiceModels = new ModelConverterEntityBased().convertToMicroServiceModel(businessModel);

        Assert.assertNotNull(microServiceModels.get(0));
    }
}
