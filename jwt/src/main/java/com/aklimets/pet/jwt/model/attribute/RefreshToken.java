package com.aklimets.pet.jwt.model.attribute;

import com.aklimets.pet.buildingblock.interfaces.DomainAttribute;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Embeddable
@Getter
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RefreshToken extends DomainAttribute<String> {

    @NotNull
    private String value;

    protected RefreshToken() {
    }
}
