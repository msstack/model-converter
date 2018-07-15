package com.grydtech.msstack.modelconverter.common;

public class ModelComponentWrapper <T extends ModelComponent> {
    private final T t;

    public ModelComponentWrapper(T t) {
        this.t = t;
    }

    public T unwrap() {
        return t;
    }

    @Override
    public boolean equals(Object ot) {
        if (ot instanceof ModelComponentWrapper) {
            return ((ModelComponentWrapper) ot).t.getId().equals(this.t.getId());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return t.getId().hashCode();
    }
}
