package com.grydtech.msstack.modelconverter.utils;

import com.grydtech.msstack.modelconverter.components.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Helpers {

    private Helpers() {
    }

    public static Collection<Entity> extractEntities(Collection<Entity> baseEntities) {
        final List<Entity> entities = new ArrayList<>(baseEntities);

        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);
            entity.getFields().stream().filter(field -> field.getType() == Constants.FIELD_TYPE.ENTITY).forEach(field -> entities.add((Entity) field.getComponent()));
        }

        return entities;
    }

    public static Collection<Event> extractEvents(Collection<Entity> entities, Collection<Contract> contracts) {
        final List<Event> events = new ArrayList<>();

        entities.forEach(businessEntity -> {
            events.addAll(businessEntity.getEvents());
        });

        contracts.forEach(contract -> {
            if (contract.getConsumes() instanceof Event) events.add((Event) contract.getConsumes());
            contract.getProducesOnSuccess().stream().filter(p -> p instanceof Event).forEach(p -> events.add((Event) p));
            contract.getProducesOnError().stream().filter(p -> p instanceof Event).forEach(p -> events.add((Event) p));
        });

        return events;
    }

    public static Collection<Request> extractRequests(Collection<Contract> contracts) {
        final List<Request> requests = new ArrayList<>();

        contracts
                .stream()
                .filter(contract -> contract.getConsumes() instanceof Request)
                .map(Contract::getConsumes)
                .forEach(request -> requests.add((Request) request));

        return requests;
    }

    public static Collection<Response> extractResponses(Collection<Contract> contracts) {
        final List<Response> responses = new ArrayList<>();

        contracts.forEach(contract -> {
            contract.getProducesOnSuccess()
                    .stream()
                    .filter(produces -> produces instanceof Response)
                    .forEach(response -> responses.add((Response) response));

            contract.getProducesOnError()
                    .stream()
                    .filter(produces -> produces instanceof Response)
                    .forEach(response -> responses.add((Response) response));
        });

        return responses;
    }

    public static Constants.FIELD_MULTIPLICITY extractMultiplicity(String ref) {
        if (ref.contains("list")) {
            return Constants.FIELD_MULTIPLICITY.MANY;
        } else {
            return Constants.FIELD_MULTIPLICITY.ONE;
        }
    }

    public static Constants.FIELD_TYPE extractType(String ref) {
        Pattern pattern = Pattern.compile(":(.*?)#");
        Matcher matcher = pattern.matcher(ref);

        if (!matcher.find()) return Constants.FIELD_TYPE.PRIMARY;

        if ("entities".equals(matcher.group(1))) {
            return Constants.FIELD_TYPE.ENTITY;
        } else if ("events".equals(matcher.group(1))) {
            return Constants.FIELD_TYPE.EVENT;
        } else if ("requests".equals(matcher.group(1))) {
            return Constants.FIELD_TYPE.REQUEST;
        } else if ("responses".equals(matcher.group(1))) {
            return Constants.FIELD_TYPE.RESPONSE;
        } else {
            return Constants.FIELD_TYPE.PRIMARY;
        }
    }

    public static String extractName(String ref) {
        Pattern pattern = Pattern.compile("#(.*?)}");
        Matcher matcher = pattern.matcher(ref);

        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return ref;
        }
    }

    public static Communication getCommunicationObject(Map<String, Request> requestMap, Map<String, Response> responseMap, Map<String, Event> eventMap, String ref) {
        Constants.FIELD_TYPE type = extractType(ref);

        switch (type) {
            case REQUEST: return requestMap.get(extractName(ref));
            case RESPONSE: return responseMap.get(extractName(ref));
            case EVENT: return eventMap.get(extractName(ref));
            default: return null;
        }
    }

    public static void fillFieldProperties(Map<String, Entity> entityMap ,Map<String, Request> requestMap, Map<String, Response> responseMap, Map<String, Event> eventMap, Field field) {
        field.setType(extractType(field.getTypeRef()));
        field.setMultiplicity(extractMultiplicity(field.getTypeRef()));
        String componentName = extractName(field.getTypeRef());

        switch (field.getType()) {
            case ENTITY: field.setComponent(entityMap.get(componentName)); break;
            case REQUEST: field.setComponent(requestMap.get(componentName)); break;
            case RESPONSE: field.setComponent(responseMap.get(componentName)); break;
            case EVENT: field.setComponent(eventMap.get(componentName)); break;
        }
    }
}
