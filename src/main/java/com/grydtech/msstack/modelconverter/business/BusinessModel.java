package com.grydtech.msstack.modelconverter.business;

import com.grydtech.msstack.modelconverter.common.Model;

import java.util.List;

public class BusinessModel extends Model {
    private List<BusinessEntity> entities;
    private List<BusinessContract> contracts;

    public List<BusinessEntity> getEntities() {
        return entities;
    }

    public void setEntities(List<BusinessEntity> entities) {
        this.entities = entities;
    }

    public List<BusinessContract> getContracts() {
        return contracts;
    }

    public void setContracts(List<BusinessContract> contracts) {
        this.contracts = contracts;
    }
}
