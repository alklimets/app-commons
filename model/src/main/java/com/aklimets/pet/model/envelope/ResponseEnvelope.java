package com.aklimets.pet.model.envelope;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "ResponseEnvelope", description = "Payload response envelope") // describes API model in swagger
public record ResponseEnvelope<T> (T data, MetaInformation meta){
}
