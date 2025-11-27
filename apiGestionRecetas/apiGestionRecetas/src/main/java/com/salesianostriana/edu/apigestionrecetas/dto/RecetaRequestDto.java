package com.salesianostriana.edu.apigestionrecetas.dto;

import com.salesianostriana.edu.apigestionrecetas.model.Dificultad;
import com.salesianostriana.edu.apigestionrecetas.model.Receta;

import java.util.Set;

public record RecetaRequestDto(
        String nombre,
        Integer tiempoPreparacionMin,
        Dificultad dificultad,
        Long categoria_id
) {

    public Receta toEntity () {
        return Receta.builder()
                .nombre(nombre)
                .tiempoPreparacionMin(tiempoPreparacionMin)
                .dificultad(dificultad)
                .build();
    }

}
