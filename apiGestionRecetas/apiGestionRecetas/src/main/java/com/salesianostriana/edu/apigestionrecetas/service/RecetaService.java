package com.salesianostriana.edu.apigestionrecetas.service;

import com.salesianostriana.edu.apigestionrecetas.dto.AnadirIngredienteDto;
import com.salesianostriana.edu.apigestionrecetas.dto.IngredienteRequestDto;
import com.salesianostriana.edu.apigestionrecetas.dto.RecetaRequestDto;
import com.salesianostriana.edu.apigestionrecetas.dto.RecetaResponseDto;
import com.salesianostriana.edu.apigestionrecetas.error.*;
import com.salesianostriana.edu.apigestionrecetas.model.Categoria;
import com.salesianostriana.edu.apigestionrecetas.model.Ingrediente;
import com.salesianostriana.edu.apigestionrecetas.model.IngredienteReceta;
import com.salesianostriana.edu.apigestionrecetas.model.Receta;
import com.salesianostriana.edu.apigestionrecetas.repository.CategoriaRepository;
import com.salesianostriana.edu.apigestionrecetas.repository.IngredienteRecetaRepository;
import com.salesianostriana.edu.apigestionrecetas.repository.IngredienteRepository;
import com.salesianostriana.edu.apigestionrecetas.repository.RecetaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecetaService {

    private final RecetaRepository recetaRepository;
    private final CategoriaRepository categoriaRepository;
    private final IngredienteRepository ingredienteRepository;
    private final IngredienteRecetaRepository ingredienteRecetaRepository;

    public List<Receta> findAll() {
        return recetaRepository.findAll();
    }

    public Receta findById(Long id) {
        return recetaRepository.findById(id)
                .orElseThrow(() -> new RecetaNotFoundException(id));
    }

    public RecetaResponseDto create(RecetaRequestDto dto) {
        if (recetaRepository.existsByNombre(dto.nombre())) {
            throw new RecetaYaCreadaException(dto.nombre());
        }

        Categoria c = categoriaRepository.findById(dto.categoria_id()).orElseThrow(() -> new CategoriaNotFoundException(dto.categoria_id()));
        return RecetaResponseDto.of(recetaRepository.save(toEntity(c,dto)));
    }

    public Receta update(Long id, RecetaRequestDto dto) {
        Receta recetaExistente = findById(id);

        if (!recetaExistente.getNombre().equals(dto.nombre())
                && recetaRepository.existsByNombre(dto.nombre())) {
            throw new RecetaYaCreadaException(dto.nombre());
        }

        recetaExistente.setNombre(dto.nombre());
        recetaExistente.setTiempoPreparacionMin(dto.tiempoPreparacionMin());
        recetaExistente.setDificultad(dto.dificultad());

        return recetaRepository.save(recetaExistente);
    }

    public void delete(Long recetaId) {
        Receta receta = recetaRepository.findById(recetaId)
                .orElseThrow(() -> new RecetaNotFoundException(recetaId));
        recetaRepository.delete(receta);
    }

    public IngredienteReceta anadirIngrediente (Long recetaId, AnadirIngredienteDto dto) {

        Receta receta = recetaRepository.findById(recetaId)
                .orElseThrow(() -> new EntityNotFoundException("Receta no encontrada"));

        Ingrediente ingrediente = ingredienteRepository.findById(dto.ingredienteId())
                .orElseThrow(() -> new EntityNotFoundException(dto.ingredienteId()));

        if (ingredienteRecetaRepository.existsByRecetaIdAndIngredienteId(recetaId, dto.ingredienteId())) {
            throw new IngredienteYaAnadidoException("Ya hay un ingrediente con ese id", "Ya hay una recete con ese id");
        }

        IngredienteReceta iR = IngredienteReceta.builder()
                .cantidad(dto.cantidad())
                .unidadMedida(dto.unidad())
                .receta(receta)
                .ingrediente(ingrediente)
                .build();

        return ingredienteRecetaRepository.save(iR);

    }

    public IngredienteReceta updateIngrediente(Long recetaId, Long ingredienteId, AnadirIngredienteDto dto) {
        Receta receta = recetaRepository.findById(recetaId)
                .orElseThrow(() -> new RecetaNotFoundException(recetaId));

        IngredienteReceta ingredienteReceta = ingredienteRecetaRepository
                .findByRecetaIdAndIngredienteId(recetaId, ingredienteId)
                .orElseThrow(() -> new EntityNotFoundException("Ingrediente no encontrado en la receta"));

        Ingrediente nuevoIngrediente = ingredienteRepository.findById(dto.ingredienteId())
                .orElseThrow(() -> new IngredienteNotFoundException(dto.ingredienteId()));

        if (!ingredienteId.equals(dto.ingredienteId()) &&
            ingredienteRecetaRepository.existsByRecetaIdAndIngredienteId(recetaId, dto.ingredienteId())) {
            throw new IngredienteYaAnadidoException("Ya hay un ingrediente con ese id", "Ya hay una receta con ese id");
        }

        ingredienteReceta.setIngrediente(nuevoIngrediente);
        ingredienteReceta.setCantidad(dto.cantidad());
        ingredienteReceta.setUnidadMedida(dto.unidad());

        return ingredienteRecetaRepository.save(ingredienteReceta);
    }

    public Receta toEntity (Categoria c,RecetaRequestDto dto) {
        return Receta.builder()
                .nombre(dto.nombre())
                .tiempoPreparacionMin(dto.tiempoPreparacionMin())
                .dificultad(dto.dificultad())
                .categoria(c)
                .build();
    }

    public IngredienteReceta toEntity (IngredienteReceta iR,Receta r, Ingrediente i) {
        return IngredienteReceta.builder()
                .cantidad(iR.getCantidad())
                .unidadMedida(iR.getUnidadMedida())
                .receta(r)
                .ingrediente(i)
                .build();
    }

}
