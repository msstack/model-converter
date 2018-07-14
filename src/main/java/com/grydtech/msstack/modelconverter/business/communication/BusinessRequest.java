package com.grydtech.msstack.modelconverter.business.communication;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class BusinessRequest extends BusinessCommunication {
    private String type;
}
