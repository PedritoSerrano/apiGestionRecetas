package com.salesianostriana.edu.apigestionrecetas.dto;

import com.salesianostriana.edu.apigestionrecetas.model.IngredienteReceta;

public record IngredienteRecetaRequestDto(
    Long id,
    double cantidad,
    String unidadMedida,
    Long receta_id,
    Long ingrediente_id

) {

    public IngredienteReceta toEntity () {
        return IngredienteReceta.builder()
                .cantidad(cantidad)
                .unidadMedida(unidadMedida)
                .build();
    }

}
