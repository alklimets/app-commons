package com.aklimets.pet.buildingblock.interfaces;

import java.io.Serializable;
import java.util.Objects;

public abstract class DomainAttribute<T>  implements Serializable {

    public abstract T getValue();

    @Override
    public String toString() {
        return getValue().toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        DomainAttribute<?> myClass = (DomainAttribute<?>) obj;
        return Objects.equals(getValue(), myClass.getValue());
    }
}
