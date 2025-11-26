package com.salesianostriana.edu.apigestionrecetas.dto;

import com.salesianostriana.edu.apigestionrecetas.model.IngredienteReceta;

public record IngredienteRecetaRequestDto(
    Long id,

) {

    public IngredienteReceta toEntity () {
        return IngredienteReceta.builder()

                .build();
    }

}
