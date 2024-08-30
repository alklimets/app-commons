package com.aklimets.pet.model.envelope;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(name = "ValidationEnvelope", description = "Validation response envelope")
public record ValidationEnvelope(String code, List<String> validationFails) {
}
