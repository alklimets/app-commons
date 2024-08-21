package com.aklimets.pet.buildingblock.interfaces;

import com.aklimets.pet.model.attribute.Role;

import java.io.Serializable;

public interface JwtClaims extends Serializable {

    DomainAttribute<String> getUsername();

    DomainAttribute<String> getId();

    Role getRole();
}
