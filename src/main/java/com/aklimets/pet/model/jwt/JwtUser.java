package com.aklimets.pet.model.jwt;

import java.util.Date;


public record JwtUser(String id, String username, Date expiredAt) {

}
