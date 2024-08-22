package com.aklimets.pet.event;

import com.aklimets.pet.buildingblock.interfaces.DomainAttribute;

public interface RequestableDomainEvent<T extends DomainAttribute<?>> extends DomainEvent {

    T getRequestId();
}
