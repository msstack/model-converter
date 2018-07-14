package com.grydtech.msstack.modelconverter.common;

import java.util.ArrayList;
import java.util.List;

public class Constants {

    public final static String SERVICE_SUFFIX = "_service";
    public final static String HANDLER_CLASS_SUFFIX = "_handler";
    public final static String ENTITY_CLASS_SUFFIX = "_entity";
    public final static String EVENT_CLASS_SUFFIX = "_event";
    public final static String REQUEST_CLASS_SUFFIX = "_request";
    public final static String RESPONSE_CLASS_SUFFIX = "_response";
    public final static String COMMAND_HANDLER_TYPE = "command";
    public final static String QUERY_HANDLER_TYPE = "query";
    public final static String EVENT_HANDLER_TYPE = "event";
    public final static List<String> BASE_TYPES = new ArrayList<>();

    static {
        BASE_TYPES.add("string");
        BASE_TYPES.add("integer");
        BASE_TYPES.add("double");
        BASE_TYPES.add("long");
        BASE_TYPES.add("float");
        BASE_TYPES.add("boolean");
    }

    private Constants() {
    }
}
