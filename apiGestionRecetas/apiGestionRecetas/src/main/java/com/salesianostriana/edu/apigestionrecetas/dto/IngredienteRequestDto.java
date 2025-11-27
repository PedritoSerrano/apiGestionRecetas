package com.salesianostriana.edu.apigestionrecetas.dto;

import com.salesianostriana.edu.apigestionrecetas.model.Ingrediente;
import com.salesianostriana.edu.apigestionrecetas.model.IngredienteReceta;

import java.util.Set;

public record IngredienteRequestDto(
        String nombre
) {

    public Ingrediente toEntity () {
        return Ingrediente.builder()
                .nombre(nombre)
                .build();
    }

}
