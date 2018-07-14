package com.grydtech.msstack.modelconverter.services.impl;

import com.grydtech.msstack.modelconverter.business.BusinessModel;
import com.grydtech.msstack.modelconverter.business.communication.BusinessEvent;
import com.grydtech.msstack.modelconverter.business.communication.BusinessRequest;
import com.grydtech.msstack.modelconverter.business.communication.BusinessResponse;
import com.grydtech.msstack.modelconverter.business.contract.BusinessContract;
import com.grydtech.msstack.modelconverter.business.entity.BusinessEntity;
import com.grydtech.msstack.modelconverter.business.server.BusinessServer;
import com.grydtech.msstack.modelconverter.common.Constants;
import com.grydtech.msstack.modelconverter.microservice.MicroServiceModel;
import com.grydtech.msstack.modelconverter.services.ModelConverter;
import com.grydtech.msstack.modelconverter.services.ModelConverterUtil;

import java.util.*;

public class DefaultModelConverter implements ModelConverter {

    @Override
    public List<MicroServiceModel> convertToMicroServiceModel(BusinessModel businessModel) {
        final List<MicroServiceModel> microServiceModels = new ArrayList<>();
        businessModel.getServers().forEach(businessServer -> {
            microServiceModels.add(generateMicroServiceModel(businessServer, businessModel.getVersion()));
        });
        return microServiceModels;
    }

    private MicroServiceModel generateMicroServiceModel(BusinessServer businessServer, String version) {
        final MicroServiceModel microServiceModel = new MicroServiceModel();
        microServiceModel.setServiceName(businessServer.getName() + Constants.SERVICE_SUFFIX);
        microServiceModel.setVersion(version);

        final List<BusinessEntity> entities = new ArrayList<>();
        final List<BusinessRequest> requests = new ArrayList<>();
        final List<BusinessResponse> responses = new ArrayList<>();
        final List<BusinessContract> contracts = new ArrayList<>();

        businessServer.getContracts().forEach(businessContract -> {
            contracts.add(businessContract);
            entities.add(businessContract.getEntity());
            requests.add(businessContract.getRequest());
            responses.add(businessContract.getResponse());
        });

        final Collection<BusinessEntity> extractedEntities = ModelConverterUtil.extractEntities(entities);
        final Collection<BusinessRequest> extractedRequests = ModelConverterUtil.extractRequests(requests);
        final Collection<BusinessResponse> extractedResponses = ModelConverterUtil.extractResponses(responses);
        final Collection<BusinessEvent> extractedEvents = ModelConverterUtil.extractEvents(entities, contracts);

        contracts.forEach(businessContract -> {
            microServiceModel.addHandlerClass(ModelConverterUtil.generateHandlerClassSchema(businessContract));
        });

        extractedEntities.forEach(businessEntity -> {
            microServiceModel.addEntityClass(ModelConverterUtil.generateEntityClassSchema(businessEntity));
        });

        extractedRequests.forEach(request -> {
            microServiceModel.addRequestClass(ModelConverterUtil.generateRequestClassSchema(request));
        });

        extractedResponses.forEach(response -> {
            microServiceModel.addResponseClass(ModelConverterUtil.generateResponseClassSchema(response));
        });

        extractedEvents.forEach(event -> {
            microServiceModel.addEventClass(ModelConverterUtil.generateEventClassSchema(event));
        });

        return microServiceModel;
    }
}
