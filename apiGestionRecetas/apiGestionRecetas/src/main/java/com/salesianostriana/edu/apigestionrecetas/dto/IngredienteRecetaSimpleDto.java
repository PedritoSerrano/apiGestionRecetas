package com.salesianostriana.edu.apigestionrecetas.dto;

import com.salesianostriana.edu.apigestionrecetas.model.IngredienteReceta;

public record IngredienteRecetaSimpleDto(
    Long id,
    double cantidad,
    String unidadMedida
) {
    public static IngredienteRecetaSimpleDto of (IngredienteReceta IR) {
        return new IngredienteRecetaSimpleDto(
                IR.getId(),
                IR.getCantidad(),
                IR.getUnidadMedida()
        );
    }
}
