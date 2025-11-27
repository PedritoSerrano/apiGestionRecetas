package com.salesianostriana.edu.apigestionrecetas.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO para añadir un ingrediente a una receta.")
public record AnadirIngredienteDto(
        @Schema(description = "ID del ingrediente", example = "3")
        Long ingredienteId,

        @Schema(description = "Cantidad del ingrediente", example = "500")
        double cantidad,

        @Schema(description = "Unidad de medida", example = "gramos")
        String unidad
) {
}