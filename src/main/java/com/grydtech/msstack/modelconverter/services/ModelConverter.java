package com.grydtech.msstack.modelconverter.services;

import com.grydtech.msstack.modelconverter.models.BusinessModel;
import com.grydtech.msstack.modelconverter.models.MicroServiceModel;

import java.util.List;

public interface ModelConverter {
    List<MicroServiceModel> convertToMicroServiceModel(BusinessModel businessModel);
}
