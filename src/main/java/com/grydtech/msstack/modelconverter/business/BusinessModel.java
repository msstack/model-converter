package com.grydtech.msstack.modelconverter.business;

import com.grydtech.msstack.modelconverter.common.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BusinessModel extends Model {

    private List<BusinessEntity> entities;
    private List<BusinessContract> contracts;
    private List<Request> requests;
    private List<Response> responses;

    public BusinessModel() {
        this.entities = new ArrayList<BusinessEntity>();
        this.contracts = new ArrayList<BusinessContract>();
        this.requests = new ArrayList<Request>();
        this.responses = new ArrayList<Response>();
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

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(Collection<Request> requests) {
        this.requests.addAll(requests);
    }

    public void setRequest(Request request) {
        this.requests.add(request);
    }

    public List<Response> getResponses() {
        return responses;
    }

    public void setResponses(Collection<Response> responses) {
        this.responses.addAll(responses);
    }

    public void setResponse(Response response) {
        this.responses.add(response);
    }

}
