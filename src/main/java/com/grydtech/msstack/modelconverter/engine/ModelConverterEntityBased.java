package com.grydtech.msstack.modelconverter.engine;

import com.grydtech.msstack.modelconverter.business.BusinessModel;
import com.grydtech.msstack.modelconverter.microservice.MicroServiceModel;

public class ModelConverterEntityBased implements ModelConverter {

    public MicroServiceModel convertToMicroServiceModel(BusinessModel businessModel) {
        throw new UnsupportedOperationException();
    }

    public BusinessModel convertToBusinessModel(MicroServiceModel microServiceModel) {
        throw new UnsupportedOperationException();
    }
}
