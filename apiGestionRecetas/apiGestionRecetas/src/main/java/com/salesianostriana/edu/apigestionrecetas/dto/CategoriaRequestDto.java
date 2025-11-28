package com.salesianostriana.edu.apigestionrecetas.dto;

import com.salesianostriana.edu.apigestionrecetas.model.Categoria;

public record CategoriaRequestDto(
    String nombre,
    String descripcion,
    Long receta_id
) {
    public Categoria toEntity () {
        return Categoria.builder()
                .nombre(nombre)
                .descripcion(descripcion)
                .build();
    }
}
