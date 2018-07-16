package com.grydtech.msstack.modelconverter.microservice.communication;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public final class EventClass extends CommunicationClass {
    private String eventGroup;
}
