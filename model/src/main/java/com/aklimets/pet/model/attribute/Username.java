package com.aklimets.pet.model.attribute;

import com.aklimets.pet.buildingblock.interfaces.DomainAttribute;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Embeddable
@Getter
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Username extends DomainAttribute<String> {

    @NotNull
    @Size(min = 3, max = 25, message = "Username length should be between 3 and 25 symbols")
    private String value;

    protected Username() {
    }
}
