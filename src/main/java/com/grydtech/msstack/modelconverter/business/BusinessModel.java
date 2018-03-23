package com.grydtech.msstack.modelconverter.business;

import com.grydtech.msstack.modelconverter.common.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BusinessModel extends Model {
    private List<BusinessEntity> entities;
    private List<BusinessContract> contracts;

    public BusinessModel() {
        this.entities = new ArrayList<BusinessEntity>();
        this.contracts = new ArrayList<BusinessContract>();
    }

    public List<BusinessEntity> getEntities() {
        return entities;
    }

    public List<BusinessContract> getContracts() {
        return contracts;
    }

    public void addEntity(BusinessEntity entity) {
        this.entities.add(entity);
    }

    public void addEntityCollection(Collection<BusinessEntity> entities) {
        this.entities.addAll(entities);
    }

    public void addContract(BusinessContract contract) {
        this.contracts.add(contract);
    }

    public void addContractCollection(Collection<BusinessContract> contracts) {
        this.contracts.addAll(contracts);
    }
}
