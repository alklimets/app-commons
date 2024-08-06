package com.aklimets.pet.buildingblock.interfaces;

import java.io.Serializable;

public interface UsernameAndIdentity extends Serializable {

    DomainAttribute<String> getUsername();

    DomainAttribute<String> getId();
}
