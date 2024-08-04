package com.aklimets.pet.model.envelope;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "ErrorResponseEnvelope", description = "Error response envelope")
public record ErrorResponseEnvelope(String errorCode, String errorMessage) {
}
