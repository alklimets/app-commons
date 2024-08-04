package com.aklimets.pet.model.security;

import com.aklimets.pet.buildingblock.interfaces.DomainAttribute;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Embeddable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Embeddable
@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class EmailAddress implements DomainAttribute<String> {

    @NotNull
    @Email
    private String value;

    protected EmailAddress() {
    }
}
