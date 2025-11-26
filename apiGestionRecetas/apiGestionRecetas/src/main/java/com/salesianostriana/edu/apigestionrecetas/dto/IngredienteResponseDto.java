package com.salesianostriana.edu.apigestionrecetas.dto;

import com.salesianostriana.edu.apigestionrecetas.model.Ingrediente;
import com.salesianostriana.edu.apigestionrecetas.model.IngredienteReceta;

import java.util.Set;

public record IngredienteResponseDto(
        Long id,
        String nombre,
        Set<IngredienteReceta> ingredienteRecetas
) {

    public IngredienteResponseDto of (Ingrediente i) {
        return new IngredienteResponseDto(
            i.getId(),
            i.getNombre(),
            i.getIngredienteRecetas()
        );
    }

}
