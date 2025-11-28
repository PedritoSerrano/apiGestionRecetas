package com.salesianostriana.edu.apigestionrecetas.controller;

    import com.salesianostriana.edu.apigestionrecetas.dto.IngredienteRequestDto;
    import com.salesianostriana.edu.apigestionrecetas.dto.IngredienteResponseDto;
    import com.salesianostriana.edu.apigestionrecetas.service.IngredienteService;
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
    @RequestMapping("/api/v1/ingredientes")
    @RequiredArgsConstructor
    @Tag(name = "Ingredientes", description = "Gestión de ingredientes para recetas")
    public class IngredienteController {

        private final IngredienteService ingredienteService;

        @Operation(summary = "Obtener todos los ingredientes")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Lista de ingredientes obtenida exitosamente")
        })
        @GetMapping
        public List<IngredienteResponseDto> findAll() {
            return ingredienteService.findAll()
                    .stream()
                    .map(IngredienteResponseDto::of)
                    .toList();
        }

        @Operation(summary = "Obtener ingrediente por ID")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Ingrediente encontrado"),
                @ApiResponse(responseCode = "404", description = "Ingrediente no encontrado")
        })
        @GetMapping("/{id}")
        public IngredienteResponseDto findById(@PathVariable Long id) {
            return IngredienteResponseDto.of(ingredienteService.findById(id));
        }

        @Operation(summary = "Crear nuevo ingrediente")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "201", description = "Ingrediente creado exitosamente"),
                @ApiResponse(responseCode = "409", description = "Ya existe un ingrediente con ese nombre")
        })
        @PostMapping
        public ResponseEntity<IngredienteResponseDto> create(
                @io.swagger.v3.oas.annotations.parameters.RequestBody(
                        description = "Datos de ingrediente",
                        required = true,
                        content = @Content(mediaType = "application/json",
                                schema = @Schema(implementation = IngredienteRequestDto.class),
                                examples = @ExampleObject("""
                                        {
                                            "nombre": "Tomate",
                                            "unidadMedida": "kg"
                                        }
                                    """)
                        )
                )
                @RequestBody IngredienteRequestDto dto
        ) {
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    IngredienteResponseDto.of(ingredienteService.create(dto))
            );
        }

        @Operation(summary = "Actualizar ingrediente existente")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Ingrediente actualizado exitosamente"),
                @ApiResponse(responseCode = "404", description = "Ingrediente no encontrado",
                        content = @Content(mediaType = "application/json",
                                schema = @Schema(implementation = org.springframework.http.ProblemDetail.class))),
                @ApiResponse(responseCode = "409", description = "Ya existe un ingrediente con ese nombre",
                        content = @Content(mediaType = "application/json",
                                schema = @Schema(implementation = org.springframework.http.ProblemDetail.class)))
        })
        @PutMapping("/{id}")
        public ResponseEntity<IngredienteResponseDto> update(
                @PathVariable Long id,
                @io.swagger.v3.oas.annotations.parameters.RequestBody(
                        description = "Datos del ingrediente actualizados ",
                        required = true,
                        content = @Content(mediaType = "application/json",
                                schema = @Schema(implementation = IngredienteRequestDto.class),
                                examples = @ExampleObject("""
                                        {
                                            "nombre": "Tomate Cherry",
                                            "unidadMedida": "kg"
                                        }
                                    """)
                        )
                )
                @RequestBody IngredienteRequestDto dto
        ) {
            return ResponseEntity.ok(
                    IngredienteResponseDto.of(ingredienteService.update(id, dto))
            );
        }

        @Operation(summary = "Eliminar ingrediente por ID")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "204", description = "Ingrediente eliminado exitosamente"),
                @ApiResponse(responseCode = "404", description = "Ingrediente no encontrado",
                        content = @Content(mediaType = "application/json",
                                schema = @Schema(implementation = org.springframework.http.ProblemDetail.class)))
        })
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> delete(@PathVariable Long id) {
            ingredienteService.delete(id);
            return ResponseEntity.noContent().build();
        }

    }