package com.salesianostriana.edu.apigestionrecetas.dto;

import com.salesianostriana.edu.apigestionrecetas.model.Ingrediente;
import com.salesianostriana.edu.apigestionrecetas.model.IngredienteReceta;
import com.salesianostriana.edu.apigestionrecetas.model.Receta;

public record IngredienteRecetaResponseDto(
        Long id,
        double cantidad,
        String unidadMedida,
        RecetaSimpleDto receta,
        IngredienteSimpleDto ingrediente
) {

    public IngredienteRecetaResponseDto of (IngredienteReceta IR) {
        return new IngredienteRecetaResponseDto(
                IR.getId(),
                IR.getCantidad(),
                IR.getUnidadMedida(),
                RecetaSimpleDto.of(IR.getReceta()),
                IngredienteSimpleDto.of(IR.getIngrediente())
        );
    }

}
