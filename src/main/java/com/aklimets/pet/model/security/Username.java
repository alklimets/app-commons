package com.aklimets.pet.model.security;

import com.aklimets.pet.buildingblock.interfaces.DomainAttribute;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Embeddable
@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class Username implements DomainAttribute<String> {

    @NotNull
    @Size(min = 3, max = 25, message = "Username length should be between 3 and 25 symbols")
    private String value;

    protected Username() {
    }
}
