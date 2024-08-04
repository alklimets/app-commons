package com.aklimets.pet.model.envelope;

import io.swagger.annotations.ApiModel;

import java.time.LocalDateTime;

@ApiModel(value = "ResponseEnvelope", description = "Payload response envelope") // describes API model in swagger
public record ResponseEnvelope<T> (T data, MetaInformation meta){
}
