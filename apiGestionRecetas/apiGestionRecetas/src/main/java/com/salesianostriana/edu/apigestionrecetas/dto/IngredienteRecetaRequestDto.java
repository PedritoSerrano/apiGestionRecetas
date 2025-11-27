package com.salesianostriana.edu.apigestionrecetas.dto;

import com.salesianostriana.edu.apigestionrecetas.model.IngredienteReceta;

public record IngredienteRecetaRequestDto(
    double cantidad,
    String unidadMedida,
    Long receta_id,
    Long ingrediente_id

) {

}
