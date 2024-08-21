package com.aklimets.pet.buildingblock.interfaces;

import com.aklimets.pet.model.attribute.RequestId;

public interface RequestableDomainEvent extends DomainEvent {

    RequestId getRequestId();
}
