package com.salesianostriana.edu.apigestionrecetas.dto;

import com.salesianostriana.edu.apigestionrecetas.model.IngredienteReceta;

public record IngredienteEnRecetaDto(
        String ingredienteNombre,
        double cantidad,
        String unidadMedida
) {

    public static IngredienteEnRecetaDto of (IngredienteReceta iR) {
        return new IngredienteEnRecetaDto(
          iR.getIngrediente().getNombre(),
          iR.getCantidad(),
          iR.getUnidadMedida()
        );
    }

}
