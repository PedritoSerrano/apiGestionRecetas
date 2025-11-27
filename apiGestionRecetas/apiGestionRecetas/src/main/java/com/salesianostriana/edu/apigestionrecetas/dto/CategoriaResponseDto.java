package com.salesianostriana.edu.apigestionrecetas.dto;

import com.salesianostriana.edu.apigestionrecetas.model.Categoria;

import java.util.Set;
import java.util.stream.Collectors;

public record CategoriaResponseDto(
        Long id,
        String nombre,
        String descripcion,
        Set<RecetaSimpleDto> recetaSimpleDto
) {

    public CategoriaResponseDto of (Categoria c) {
        return new CategoriaResponseDto(
                c.getId(),
                c.getNombre(),
                c.getDescripcion(),
                c.getReceta().stream().map(RecetaSimpleDto::of).collect(Collectors.toSet())
        );
    }

}
