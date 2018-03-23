package com.grydtech.msstack.modelconverter.engine;

import com.grydtech.msstack.modelconverter.business.BusinessModel;
import com.grydtech.msstack.modelconverter.microservice.MicroServiceModel;

import java.io.File;

public interface ModelReader {
    BusinessModel readBusinessModel(File file);
    MicroServiceModel readMicroServiceModel(File file);
}
