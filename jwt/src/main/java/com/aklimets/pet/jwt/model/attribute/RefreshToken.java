package com.aklimets.pet.jwt.model.attribute;

import com.aklimets.pet.buildingblock.interfaces.DomainAttribute;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Embeddable
@Getter
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RefreshToken extends DomainAttribute<String> {

    @NotNull
    @Min(value = 3, message = "Password should be min 3 characters long")
    @Max(value = 25, message = "Password should be max 25 characters long")
    private String value;

    protected RefreshToken() {
    }
}
