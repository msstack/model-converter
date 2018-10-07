package com.grydtech.msstack.modelconverter.services;

import com.grydtech.msstack.modelconverter.models.BusinessModel;
import com.grydtech.msstack.modelconverter.models.MicroServiceModel;

import java.io.File;

public interface ModelReader {
    BusinessModel readBusinessModel(File file);

    MicroServiceModel readMicroServiceModel(File file);
}
