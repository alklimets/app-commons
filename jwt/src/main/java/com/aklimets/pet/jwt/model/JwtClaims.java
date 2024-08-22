package com.aklimets.pet.jwt.model;

import com.aklimets.pet.buildingblock.interfaces.DomainAttribute;
import com.aklimets.pet.jwt.model.attribute.Role;

import java.io.Serializable;

public interface JwtClaims extends Serializable {

    DomainAttribute<String> getUsername();

    DomainAttribute<String> getId();

    Role getRole();
}
