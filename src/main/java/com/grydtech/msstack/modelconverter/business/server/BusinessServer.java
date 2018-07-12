package com.grydtech.msstack.modelconverter.business.server;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.grydtech.msstack.modelconverter.business.contract.BusinessContract;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
public class BusinessServer {

    private String id;
    private String name;
    private final List<String> contractIds = new ArrayList<>();
    @JsonIgnore
    private final List<BusinessContract> contracts = new ArrayList<>();

    public void addContract(BusinessContract contract) {
        this.contracts.add(contract);
    }

    public void addContractCollection(Collection<BusinessContract> contracts) {
        this.contracts.addAll(contracts);
    }
}
