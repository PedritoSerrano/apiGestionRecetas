package com.salesianostriana.edu.apigestionrecetas.dto;

import com.salesianostriana.edu.apigestionrecetas.model.IngredienteReceta;

public record IngredienteRecetaDto(
        String ingredienteNombre,
        double cantidad,
        String unidadMedida
) {

    public static IngredienteRecetaDto of (IngredienteReceta iR) {
        return new IngredienteRecetaDto(
          iR.getIngrediente().getNombre(),
          iR.getCantidad(),
          iR.getUnidadMedida()
        );
    }

}
