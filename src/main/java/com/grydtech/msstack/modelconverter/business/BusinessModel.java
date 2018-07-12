package com.grydtech.msstack.modelconverter.business;

import com.grydtech.msstack.modelconverter.business.contract.BusinessContract;
import com.grydtech.msstack.modelconverter.business.contract.ContractRequest;
import com.grydtech.msstack.modelconverter.business.contract.ContractResponse;
import com.grydtech.msstack.modelconverter.business.entity.BusinessEntity;
import com.grydtech.msstack.modelconverter.business.server.BusinessServer;
import com.grydtech.msstack.modelconverter.common.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class BusinessModel extends Model {

    private final List<BusinessEntity> entities = new ArrayList<>();
    private final List<BusinessContract> contracts = new ArrayList<>();
    private final List<ContractRequest> requests = new ArrayList<>();
    private final List<ContractResponse> responses = new ArrayList<>();
    private final List<BusinessServer> servers = new ArrayList<>();

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

    public void addRequest(ContractRequest request) {
        this.requests.add(request);
    }

    public void addRequestCollection(Collection<ContractRequest> requests) {
        this.requests.addAll(requests);
    }

    public void addResponse(ContractResponse response) {
        this.responses.add(response);
    }

    public void addResponseCollection(Collection<ContractResponse> responses) {
        this.responses.addAll(responses);
    }

    public void addServer(BusinessServer server) {
        this.servers.add(server);
    }

    public void addServerCollection(Collection<BusinessServer> servers) {
        this.servers.addAll(servers);
    }
}
