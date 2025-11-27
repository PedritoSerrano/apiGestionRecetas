package com.salesianostriana.edu.apigestionrecetas.dto;

import com.salesianostriana.edu.apigestionrecetas.model.Ingrediente;
import com.salesianostriana.edu.apigestionrecetas.model.IngredienteReceta;

import java.util.Set;
import java.util.stream.Collectors;

public record IngredienteResponseDto(
        Long id,
        String nombre,
        Set<IngredienteRecetaSimpleDto> ingredienteRecetas
) {

    public static IngredienteResponseDto of (Ingrediente i) {
        return new IngredienteResponseDto(
            i.getId(),
            i.getNombre(),
            i.getIngredienteRecetas().stream().map(IngredienteRecetaSimpleDto::of).collect(Collectors.toSet())
        );
    }

}
