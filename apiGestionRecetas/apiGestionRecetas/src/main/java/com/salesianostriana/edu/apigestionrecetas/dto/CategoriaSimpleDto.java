package com.salesianostriana.edu.apigestionrecetas.dto;

import com.salesianostriana.edu.apigestionrecetas.model.Categoria;

public record CategoriaSimpleDto(
        Long id,
        String nombre
) {

    public static CategoriaSimpleDto of (Categoria c) {
        return new CategoriaSimpleDto(
                c.getId(),
                c.getNombre()
        );
    }

}
