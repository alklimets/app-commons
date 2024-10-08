package com.aklimets.pet.swagger.annotation;

import com.aklimets.pet.model.envelope.ErrorResponseEnvelope;
import com.aklimets.pet.model.envelope.ValidationEnvelope;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation is used to provide common API responses
 * Each error code contains info about related return schema type
 */
@ApiResponses(
        value = {
                @ApiResponse(responseCode = "400", description = "Bad request",
                        content = {@Content(schema = @Schema(implementation = ErrorResponseEnvelope.class))}),
                @ApiResponse(responseCode = "401", description = "Unauthorized",
                        content = {@Content(schema = @Schema(implementation = ErrorResponseEnvelope.class))}),
                @ApiResponse(responseCode = "403", description = "Access denied",
                        content = {@Content(schema = @Schema(implementation = ErrorResponseEnvelope.class))}),
                @ApiResponse(responseCode = "404", description = "Not found",
                        content = {@Content(schema = @Schema(implementation = ErrorResponseEnvelope.class))}),
                @ApiResponse(responseCode = "422", description = "Validation failed",
                        content = {@Content(schema = @Schema(implementation = ValidationEnvelope.class))}),
                @ApiResponse(responseCode = "500", description = "Internal server error",
                        content = {@Content(schema = @Schema(implementation = ErrorResponseEnvelope.class))})
        }
)
@Target({ElementType.METHOD, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DefaultSwaggerEndpoint {
}
