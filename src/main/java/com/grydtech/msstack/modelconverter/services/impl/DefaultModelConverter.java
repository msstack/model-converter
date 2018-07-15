package com.grydtech.msstack.modelconverter.services.impl;

import com.grydtech.msstack.modelconverter.business.BusinessModel;
import com.grydtech.msstack.modelconverter.business.communication.BusinessEvent;
import com.grydtech.msstack.modelconverter.business.communication.BusinessRequest;
import com.grydtech.msstack.modelconverter.business.communication.BusinessResponse;
import com.grydtech.msstack.modelconverter.business.contract.BusinessContract;
import com.grydtech.msstack.modelconverter.business.entity.BusinessEntity;
import com.grydtech.msstack.modelconverter.business.server.BusinessServer;
import com.grydtech.msstack.modelconverter.common.Constants;
import com.grydtech.msstack.modelconverter.common.ModelComponentWrapper;
import com.grydtech.msstack.modelconverter.microservice.MicroServiceModel;
import com.grydtech.msstack.modelconverter.services.ModelConverter;
import com.grydtech.msstack.modelconverter.common.ModelConverterUtil;

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

        final Collection<BusinessContract> contracts = new ArrayList<>();
        final Collection<BusinessEntity> entities = new ArrayList<>();
        final Collection<BusinessRequest> requests = new ArrayList<>();
        final Collection<BusinessResponse> responses = new ArrayList<>();

        businessServer.getContracts().forEach(businessContract -> {
            contracts.add(businessContract);
            entities.add(businessContract.getEntity());

            if (!businessContract.getHandler().getType().equals(Constants.EVENT_HANDLER_TYPE)) {
                requests.add((BusinessRequest) businessContract.getRequest());
                responses.add((BusinessResponse) businessContract.getResponse());
            }
        });

        final Collection<BusinessEntity> extractedEntities = ModelConverterUtil.extractEntities(entities);
        final Collection<BusinessEvent> extractedEvents = ModelConverterUtil.extractEvents(entities, contracts);

        contracts.stream()
                .map(ModelComponentWrapper::new)
                .distinct()
                .map(ModelComponentWrapper::unwrap)
                .forEach(businessContract -> {
                    microServiceModel.addHandlerClass(ModelConverterUtil.generateHandlerClassSchema(businessContract));
                });

        requests.stream()
                .map(ModelComponentWrapper::new)
                .distinct()
                .map(ModelComponentWrapper::unwrap)
                .forEach(request -> {
                    microServiceModel.addRequestClass(ModelConverterUtil.generateRequestClassSchema(request));
                });

        responses.stream()
                .map(ModelComponentWrapper::new)
                .distinct()
                .map(ModelComponentWrapper::unwrap)
                .forEach(response -> {
                    microServiceModel.addResponseClass(ModelConverterUtil.generateResponseClassSchema(response));
                });

        extractedEntities.stream()
                .map(ModelComponentWrapper::new)
                .distinct()
                .map(ModelComponentWrapper::unwrap)
                .forEach(businessEntity -> {
                    if (entities.contains(businessEntity)) {
                        microServiceModel.addEntityClass(ModelConverterUtil.generateEntityClassSchema(businessEntity, true));
                    } else {
                        microServiceModel.addEntityClass(ModelConverterUtil.generateEntityClassSchema(businessEntity, false));
                    }
                });

        extractedEvents.stream()
                .map(ModelComponentWrapper::new)
                .distinct()
                .map(ModelComponentWrapper::unwrap)
                .forEach(event -> {
                    microServiceModel.addEventClass(ModelConverterUtil.generateEventClassSchema(event));
                });

        return microServiceModel;
    }
}
