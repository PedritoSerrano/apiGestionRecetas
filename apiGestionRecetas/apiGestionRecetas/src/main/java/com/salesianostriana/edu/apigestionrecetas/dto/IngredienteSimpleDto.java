package com.salesianostriana.edu.apigestionrecetas.dto;

import com.salesianostriana.edu.apigestionrecetas.model.Ingrediente;

public record IngredienteSimpleDto(
        Long id,
        String nombre
) {
    public static IngredienteSimpleDto of (Ingrediente i) {
        return new IngredienteSimpleDto(
                i.getId(),
                i.getNombre()
        );
    }
}
