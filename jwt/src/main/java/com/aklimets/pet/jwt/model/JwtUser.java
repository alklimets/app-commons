package com.aklimets.pet.jwt.model;

import com.aklimets.pet.buildingblock.interfaces.DomainAttribute;
import com.aklimets.pet.jwt.model.attribute.Role;

import java.util.Date;


public record JwtUser (DomainAttribute<String> id, DomainAttribute<String> username, DomainAttribute<Date> expiredAt, Role role) {

}
