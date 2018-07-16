package com.grydtech.msstack.modelconverter.business;

import com.grydtech.msstack.modelconverter.business.contract.BusinessContract;
import com.grydtech.msstack.modelconverter.business.communication.BusinessEvent;
import com.grydtech.msstack.modelconverter.business.communication.BusinessRequest;
import com.grydtech.msstack.modelconverter.business.communication.BusinessResponse;
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
public final class BusinessModel extends Model {

    private final List<BusinessEntity> entities = new ArrayList<>();
    private final List<BusinessEvent> events = new ArrayList<>();
    private final List<BusinessContract> contracts = new ArrayList<>();
    private final List<BusinessRequest> requests = new ArrayList<>();
    private final List<BusinessResponse> responses = new ArrayList<>();
    private final List<BusinessServer> servers = new ArrayList<>();

    public void addEntity(BusinessEntity entity) {
        this.entities.add(entity);
    }

    public void addEntityCollection(Collection<BusinessEntity> entities) {
        this.entities.addAll(entities);
    }

    public void addEvent(BusinessEvent event) {
        this.events.add(event);
    }

    public void addEventCollection(Collection<BusinessEvent> events) {
        this.events.addAll(events);
    }

    public void addContract(BusinessContract contract) {
        this.contracts.add(contract);
    }

    public void addContractCollection(Collection<BusinessContract> contracts) {
        this.contracts.addAll(contracts);
    }

    public void addRequest(BusinessRequest request) {
        this.requests.add(request);
    }

    public void addRequestCollection(Collection<BusinessRequest> requests) {
        this.requests.addAll(requests);
    }

    public void addResponse(BusinessResponse response) {
        this.responses.add(response);
    }

    public void addResponseCollection(Collection<BusinessResponse> responses) {
        this.responses.addAll(responses);
    }

    public void addServer(BusinessServer server) {
        this.servers.add(server);
    }

    public void addServerCollection(Collection<BusinessServer> servers) {
        this.servers.addAll(servers);
    }
}
