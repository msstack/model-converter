package com.grydtech.msstack.modelconverter.business;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusinessContract {

    private String id;
    private String entityId;
    private ContractHandler handler;
    private Request request;
    private Response response;
    private List<String> events;
    private BusinessEntity entity;
    private String requestId;
    private String responseId;

    public BusinessContract() {
        this.events = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public ContractHandler getHandler() {
        return handler;
    }

    public void setHandler(ContractHandler handler) {
        this.handler = handler;
    }

    public List<String> getEvents() {
        return events;
    }

    public BusinessEntity getEntity() {
        return entity;
    }

    public void setEntity(BusinessEntity entity) {
        this.entity = entity;
    }

    public void addEvent(String event) {
        this.events.add(event);
    }

    public void addEventCollection(Collection<String> events) {
        this.events.addAll(events);
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getResponseId() {
        return responseId;
    }

    public void setResponseId(String responseId) {
        this.responseId = responseId;
    }

}
