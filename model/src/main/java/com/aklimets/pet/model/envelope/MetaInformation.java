package com.aklimets.pet.model.envelope;

import java.time.LocalDateTime;

public record MetaInformation(String url, String method, String requestId, LocalDateTime occurredAt) {
}
