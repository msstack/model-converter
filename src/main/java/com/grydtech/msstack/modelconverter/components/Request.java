package com.grydtech.msstack.modelconverter.components;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.grydtech.msstack.modelconverter.utils.Constants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public final class Request extends Communication {
}
