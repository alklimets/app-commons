package com.aklimets.pet.model.security;

import com.aklimets.pet.buildingblock.interfaces.DomainAttribute;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Embeddable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Embeddable
@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class RefreshToken implements DomainAttribute<String> {

    @NotNull
    @Min(value = 3, message = "Password should be min 3 characters long")
    @Max(value = 25, message = "Password should be max 25 characters long")
    private String value;

    protected RefreshToken() {
    }
}
