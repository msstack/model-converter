package com.grydtech.msstack.modelconverter.business;

import com.grydtech.msstack.modelconverter.common.Model;

import java.util.ArrayList;
import java.util.List;

public class BusinessModel extends Model {
    private List<BusinessEntity> businessEntities;
    private List<BusinessContract> businessContracts;

    public BusinessModel(String version) {
        super(version);
        businessEntities = new ArrayList<BusinessEntity>();
        businessContracts = new ArrayList<BusinessContract>();
    }

    public List<BusinessEntity> getBusinessEntities() {
        return businessEntities;
    }

    public List<BusinessContract> getBusinessContracts() {
        return businessContracts;
    }
}
