package com.aklimets.pet.model.jwt;

import com.aklimets.pet.buildingblock.interfaces.DomainAttribute;

import java.util.Date;


public record JwtUser (DomainAttribute<String> id, DomainAttribute<String> username, DomainAttribute<Date> expiredAt) {

}
