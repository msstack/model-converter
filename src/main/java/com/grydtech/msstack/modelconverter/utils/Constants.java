package com.grydtech.msstack.modelconverter.utils;

public class Constants {
    public enum HANDLER_TYPE {
        COMMAND,
        QUERY,
        EVENT
    }

    public enum FIELD_TYPE {
        PRIMARY,
        ENTITY,
        EVENT,
        REQUEST,
        RESPONSE
    }

    public enum FIELD_MULTIPLICITY {
        ONE,
        MANY
    }

    public enum CONSTRAINT {
        PRIMARY_KEY,
        AUTO_GENERATED,
        UNIQUE,
        NOT_NULL
    }
}
