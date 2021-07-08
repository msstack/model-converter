package com.grydtech.msstack.modelconverter.models;

import com.grydtech.msstack.modelconverter.components.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class MicroServiceModel extends Model {
    private String serviceName;
    private final List<Entity> entities = new ArrayList<>();
    private final List<Event> events = new ArrayList<>();
    private final List<Request> requests = new ArrayList<>();
    private final List<Response> responses = new ArrayList<>();
    private final List<Contract> contracts = new ArrayList<>();

    public void addEntity(Entity entity) {
        this.entities.add(entity);
    }

    public void addRequest(Request request) {
        this.requests.add(request);
    }

    public void addResponse(Response response) {
        this.responses.add(response);
    }

    public void addEvent(Event event) {
        this.events.add(event);
    }

    public void addContract(Contract contract) {
        this.contracts.add(contract);
    }
}
