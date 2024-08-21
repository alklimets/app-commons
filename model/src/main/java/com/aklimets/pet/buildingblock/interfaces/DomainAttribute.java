package com.aklimets.pet.buildingblock.interfaces;

import java.io.Serializable;

public abstract class DomainAttribute<T>  implements Serializable {

    public abstract T getValue();

    @Override
    public String toString() {
        return getValue().toString();
    }
}
