package com.salesianostriana.edu.apigestionrecetas.dto;

import com.salesianostriana.edu.apigestionrecetas.model.Ingrediente;
import com.salesianostriana.edu.apigestionrecetas.model.IngredienteReceta;

import java.util.Set;

public record IngredienteRequestDto(
        Long id,
        String nombre,
        Set<IngredienteReceta> ingredienteRecetas
) {

    public Ingrediente toEntity () {
        return Ingrediente.builder()
                .nombre(nombre)
                .ingredienteRecetas(ingredienteRecetas)
                .build();
    }

}
