package com.aklimets.pet.jwt.model.attribute;

import com.aklimets.pet.buildingblock.interfaces.DomainAttribute;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AccessToken extends DomainAttribute<String> {

    @NotNull
    private String value;
}
