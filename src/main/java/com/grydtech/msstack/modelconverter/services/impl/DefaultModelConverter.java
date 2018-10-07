package com.grydtech.msstack.modelconverter.services.impl;

import com.grydtech.msstack.modelconverter.models.BusinessModel;
import com.grydtech.msstack.modelconverter.components.Event;
import com.grydtech.msstack.modelconverter.components.Request;
import com.grydtech.msstack.modelconverter.components.Response;
import com.grydtech.msstack.modelconverter.components.Contract;
import com.grydtech.msstack.modelconverter.components.Entity;
import com.grydtech.msstack.modelconverter.components.ModelComponentWrapper;
import com.grydtech.msstack.modelconverter.models.MicroServiceModel;
import com.grydtech.msstack.modelconverter.services.ModelConverter;
import com.grydtech.msstack.modelconverter.utils.Helpers;

import java.util.*;

public class DefaultModelConverter implements ModelConverter {

    @Override
    public List<MicroServiceModel> convertToMicroServiceModel(BusinessModel businessModel) {
        final List<MicroServiceModel> microServiceModels = new ArrayList<>();
        groupContractsByEntity(businessModel.getContracts()).forEach((name, contracts) -> {
            microServiceModels.add(generateMicroServiceModel(contracts, businessModel.getSchemaVersion()));
        });
        return microServiceModels;
    }

    private MicroServiceModel generateMicroServiceModel(List<Contract> contracts, String schemaVersion) {
        final MicroServiceModel microServiceModel = new MicroServiceModel();
        microServiceModel.setServiceName(contracts.get(0).getEntity().getName());
        microServiceModel.setSchemaVersion(schemaVersion);

        final Collection<Entity> baseEntities = new ArrayList<>();
        baseEntities.add(contracts.get(0).getEntity());

        final Collection<Entity> entities = Helpers.extractEntities(baseEntities);
        final Collection<Event> events = Helpers.extractEvents(entities, contracts);
        final Collection<Request> requests = Helpers.extractRequests(contracts);
        final Collection<Response> responses = Helpers.extractResponses(contracts);

        entities.stream()
                .map(ModelComponentWrapper::new)
                .distinct()
                .map(ModelComponentWrapper::unwrap)
                .forEach(microServiceModel::addEntity);

        events.stream()
                .map(ModelComponentWrapper::new)
                .distinct()
                .map(ModelComponentWrapper::unwrap)
                .forEach(microServiceModel::addEvent);

        requests.stream()
                .map(ModelComponentWrapper::new)
                .distinct()
                .map(ModelComponentWrapper::unwrap)
                .forEach(microServiceModel::addRequest);

        responses.stream()
                .map(ModelComponentWrapper::new)
                .distinct()
                .map(ModelComponentWrapper::unwrap)
                .forEach(microServiceModel::addResponse);

        contracts.stream()
                .map(ModelComponentWrapper::new)
                .distinct()
                .map(ModelComponentWrapper::unwrap)
                .forEach(microServiceModel::addContract);


        return microServiceModel;
    }

    private static Map<String, List<Contract>> groupContractsByEntity(List<Contract> contracts) {
        final Map<String, List<Contract>> groups = new HashMap<>();

        contracts.forEach(contract -> {
            if (groups.containsKey(contract.getEntity().getName())) {
                groups.get(contract.getEntity().getName()).add(contract);
            } else {
                List<Contract> cs = new ArrayList<>();
                cs.add(contract);
                groups.put(contract.getEntity().getName(), cs);
            }
        });

        return groups;
    }
}
