package com.salesianostriana.edu.apigestionrecetas.dto;

import com.salesianostriana.edu.apigestionrecetas.model.Dificultad;
import com.salesianostriana.edu.apigestionrecetas.validation.UniqueNombre;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record RecetaRequestDto(
        @UniqueNombre
        String nombre,
        @Min(1)
        Integer tiempoPreparacionMin,
        Dificultad dificultad,
        @NotNull (message = "El Id de la categoría de la receta no puede estar vacío")
        Long categoria_id
) {}
