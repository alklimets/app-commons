package com.aklimets.pet.model.envelope;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "ErrorResponseEnvelope", description = "Error response envelope")
public record ErrorResponseEnvelope(String errorCode, String errorMessage) {
}
