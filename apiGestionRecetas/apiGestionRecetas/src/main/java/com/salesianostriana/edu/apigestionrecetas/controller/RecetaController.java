package com.salesianostriana.edu.apigestionrecetas.controller;

import com.salesianostriana.edu.apigestionrecetas.dto.AnadirIngredienteDto;
import com.salesianostriana.edu.apigestionrecetas.dto.IngredienteEnRecetaDto;
import com.salesianostriana.edu.apigestionrecetas.dto.RecetaRequestDto;
import com.salesianostriana.edu.apigestionrecetas.dto.RecetaResponseDto;
import com.salesianostriana.edu.apigestionrecetas.model.IngredienteReceta;
import com.salesianostriana.edu.apigestionrecetas.service.RecetaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recetas")
@RequiredArgsConstructor
@Tag(name = "Recetas", description = "Gestión de recetas de cocina")
public class RecetaController {

    private final RecetaService recetaService;

    @Operation(summary = "Obtener todas las recetas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de recetas obtenida exitosamente")
    })
    @GetMapping
    public List<RecetaResponseDto> findAll() {
        return recetaService.findAll()
                .stream()
                .map(RecetaResponseDto::of)
                .toList();
    }

    @Operation(summary = "Obtener receta por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Receta encontrada"),
            @ApiResponse(responseCode = "404", description = "Receta no encontrada")
    })
    @GetMapping("/{id}")
    public RecetaResponseDto findById(@PathVariable Long id) {
        return RecetaResponseDto.of(recetaService.findById(id));
    }

    @Operation(summary = "Crear nueva receta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Receta creada exitosamente"),
            @ApiResponse(responseCode = "409", description = "Ya existe una receta con ese nombre")
    })
    @PostMapping
    public ResponseEntity<RecetaResponseDto> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos de receta",
                    required = true,
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RecetaRequestDto.class),
                            examples = @ExampleObject("""
                                    {
                                        "nombre": "Migas extremeñas",
                                        "tiempoPreparacionMin": 60,
                                        "dificultad": "MEDIA",
                                        "categoriaId": 1
                                    }
                                """)
                    )
            )
            @RequestBody RecetaRequestDto dto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                RecetaResponseDto.of(recetaService.create(dto))
        );
    }

    @Operation(summary = "Actualizar receta existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Receta actualizada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Receta no encontrada"),
            @ApiResponse(responseCode = "409", description = "Ya existe una receta con ese nombre")
    })
    @PutMapping("/{id}")
    public RecetaResponseDto update(
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos actualizados de la receta",
                    required = true,
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RecetaRequestDto.class),
                            examples = @ExampleObject("""
                                    {
                                        "nombre": "Migas Extremeñas",
                                        "tiempoPreparacionMin": 60,
                                        "dificultad": "MEDIA",
                                        "categoriaId": 1
                                    }
                                """)
                    )
            )
            @RequestBody RecetaRequestDto dto
    ) {
        return RecetaResponseDto.of(recetaService.update(id, dto));
    }

    @Operation(summary = "Eliminar receta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Receta eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Receta no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        recetaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{recetaId}/ingredientes")
    public ResponseEntity<IngredienteEnRecetaDto> anadirIngrediente(
            @PathVariable Long recetaId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = """
                            DTO para añadir ingrediente a receta.
                            """,
                    required = true,
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AnadirIngredienteDto.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Pan",
                                            description = "Pan para las migas",
                                            value = """
                                                    {
                                                        "ingredienteId": 1,
                                                        "cantidad": "1",
                                                        "unidad": "kilogramo"
                                                    }
                                                    """
                                    ),
                                    @ExampleObject(
                                            name = "Agua",
                                            description = "Agua para las migas",
                                            value = """
                                                    {
                                                        "ingredienteId": 2,
                                                        "cantidad": "500",
                                                        "unidad": "mililitros"
                                                    }
                                                    """
                                    ),
                                    @ExampleObject(
                                            name = "Chorizo",
                                            description = "Chorizo para las migas",
                                            value = """
                                                    {
                                                        "ingredienteId": 3,
                                                        "cantidad": "3",
                                                        "unidad": "Cucharadas"
                                                    }
                                                    """
                                    )
                            }
                    )
            )
            @RequestBody AnadirIngredienteDto dto) {
        IngredienteReceta iR = recetaService.anadirIngrediente(recetaId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(IngredienteEnRecetaDto.of(iR));
    }

}