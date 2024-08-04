package com.aklimets.pet.buildingblock.interfaces;

import java.io.Serializable;

public interface DomainAttribute<T>  extends Serializable {

    T getValue();
}
