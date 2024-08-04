package com.aklimets.pet.buildingblock.interfaces;

public interface UsernameAndIdentity {

    DomainAttribute<String> getUsername();

    DomainAttribute<String> getId();
}
