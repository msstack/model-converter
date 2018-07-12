package com.grydtech.msstack.modelconverter.business.contract;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class ContractRequest extends ContractCommunication {
    private String type;
}
