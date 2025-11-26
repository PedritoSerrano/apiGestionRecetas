package com.salesianostriana.edu.apigestionrecetas.dto;

import com.salesianostriana.edu.apigestionrecetas.model.Dificultad;
import com.salesianostriana.edu.apigestionrecetas.model.Receta;

public record RecetaSimpleDto(
    Long id,
    String nombre,
    Integer tiempoPreparacionMin,
    Dificultad dificultad
) {
    public static RecetaSimpleDto of (Receta r) {
        return new RecetaSimpleDto(
                r.getId(),
                r.getNombre(),
                r.getTiempoPreparacionMin(),
                r.getDificultad()
        );
    }
}
