package com.salesianostriana.edu.apigestionrecetas.dto;

import com.salesianostriana.edu.apigestionrecetas.model.Dificultad;
import com.salesianostriana.edu.apigestionrecetas.model.Receta;

import java.util.Set;
import java.util.stream.Collectors;

public record RecetaResponseDto(
        Long id,
        String nombre,
        Integer tiempoPreparacionMin,
        Dificultad dificultad,
        CategoriaSimpleDto categoriaSimpleDto,
        Set<IngredienteRecetaSimpleDto> ingredienteRecetaSimpleDto
) {

    public static RecetaResponseDto of (Receta r) {
        return new RecetaResponseDto(
                r.getId(),
                r.getNombre(),
                r.getTiempoPreparacionMin(),
                r.getDificultad(),
                CategoriaSimpleDto.of(r.getCategoria()),
                r.getIngredienteRecetas().stream().map(IngredienteRecetaSimpleDto::of).collect(Collectors.toSet())
        );
    }

}
