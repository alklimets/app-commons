package com.aklimets.pet.model.envelope;

import io.swagger.annotations.ApiModel;

import java.util.List;

@ApiModel(value = "ValidationEnvelope", description = "Validation response envelope")
public record ValidationEnvelope(String code, List<String> validationFails) {
}
