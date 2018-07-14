package com.grydtech.msstack.modelconverter.services;

import com.grydtech.msstack.modelconverter.business.BusinessModel;
import com.grydtech.msstack.modelconverter.microservice.MicroServiceModel;
import com.grydtech.msstack.modelconverter.services.impl.DefaultModelConverter;
import com.grydtech.msstack.modelconverter.services.impl.DefaultModelReader;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.util.List;

public class DefaultModelReaderTest {
    @Test
    public void readBusinessModelTest() {
        File jsonFile = null;
        try {
            jsonFile = new File(this.getClass().getResource("/sample-business-model.json").toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        BusinessModel businessModel = new DefaultModelReader().readBusinessModel(jsonFile);
        List<MicroServiceModel> microServiceModels = new DefaultModelConverter().convertToMicroServiceModel(businessModel);

        Assert.assertNotNull(microServiceModels.get(0));
    }
}
