package com.grydtech.msstack.modelconverter.engine;

import com.grydtech.msstack.modelconverter.business.BusinessModel;
import com.grydtech.msstack.modelconverter.microservice.MicroServiceModel;

public interface ModelConverter {
    MicroServiceModel convertToMicroServiceModel(BusinessModel businessModel);
    BusinessModel convertToBusinessModel(MicroServiceModel microServiceModel);
}
