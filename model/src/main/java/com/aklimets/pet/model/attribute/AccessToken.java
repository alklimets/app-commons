package com.aklimets.pet.model.attribute;

import com.aklimets.pet.buildingblock.interfaces.DomainAttribute;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class AccessToken implements DomainAttribute<String> {

    @NotNull
    @Min(value = 3, message = "Password should be min 3 characters long")
    @Max(value = 25, message = "Password should be max 25 characters long")
    private String value;
}
