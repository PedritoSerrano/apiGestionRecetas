package com.salesianostriana.edu.apigestionrecetas.controller;

import com.salesianostriana.edu.apigestionrecetas.dto.CategoriaRequestDto;
import com.salesianostriana.edu.apigestionrecetas.dto.CategoriaResponseDto;
import com. salesianostriana.edu. apigestionrecetas.model.Categoria;
import com. salesianostriana.edu. apigestionrecetas.service. CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses. ApiResponses;
import io. swagger.v3.oas. annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http. HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind. annotation.*;

import java.util. List;

@RestController
@RequestMapping("/api/v1/categorias")
@RequiredArgsConstructor
@Tag(name = "Categorías", description = "Gestión de categorías de recetas")
public class CategoriaController {

    private final CategoriaService categoriaService;

    @Operation(summary = "Obtener todas las categorías")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de categorías obtenida exitosamente")
    })
    @GetMapping
    public List<CategoriaResponseDto> findAll() {
        return categoriaService.findAll()
            .stream()
            .map(CategoriaResponseDto::of)
            .toList();
}
    @Operation(summary = "Obtener categoría por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoría encontrada"),
            @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    })
    @GetMapping("/{id}")
    public CategoriaResponseDto findById(@PathVariable Long id) {
        return CategoriaResponseDto.of(categoriaService.findById(id));
    }

    @Operation(summary = "Crear nueva categoría")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Categoría creada exitosamente"),
            @ApiResponse(responseCode = "409", description = "Ya existe una categoría con ese nombre")
    })
    @PostMapping
    public ResponseEntity<CategoriaResponseDto> create(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Datos de categoría",
                required = true,
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = CategoriaRequestDto.class),
                        examples = @ExampleObject("""
                                    {
                                        "nombre": "Solomillo a la pimienta",
                                        "descripcion": "Plato principal de una calidad superior"
                                    }
                                """)
                )
        )
        @RequestBody CategoriaRequestDto dto
            ){
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    CategoriaResponseDto.of(categoriaService.create(dto))
            );
    }

    @Operation(summary = "Actualizar categoría existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoría actualizada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Categoría no encontrada"),
            @ApiResponse(responseCode = "409", description = "Ya existe una categoría con ese nombre")
    })
    @PutMapping("/{id}")
    public CategoriaResponseDto update(
        @PathVariable Long id,
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Datos actualizados de la categoría",
                required = true,
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = CategoriaRequestDto.class),
                        examples = @ExampleObject("""
                                    {
                                        "nombre": "Solomillo a la pimienta",
                                        "descripcion": "Plato principal de una calidad superior"
                                    }
                                """)
                )
        )
        @RequestBody CategoriaRequestDto dto
            ){
            return CategoriaResponseDto.of(categoriaService.update(id, dto)
            );

    }

    @Operation(summary = "Eliminar categoría")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Categoría eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        categoriaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}