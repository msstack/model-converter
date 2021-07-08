package com.grydtech.msstack.modelconverter.services;

import com.grydtech.msstack.modelconverter.models.Model;

import java.io.File;

public interface ModelWriter {
    void writeModel(File file, Model model);
}
