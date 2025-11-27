package com.salesianostriana.edu.apigestionrecetas.service;

import com.salesianostriana.edu.apigestionrecetas.dto.AnadirIngredienteDto;
import com.salesianostriana.edu.apigestionrecetas.dto.IngredienteRequestDto;
import com.salesianostriana.edu.apigestionrecetas.dto.RecetaRequestDto;
import com.salesianostriana.edu.apigestionrecetas.error.*;
import com.salesianostriana.edu.apigestionrecetas.model.Categoria;
import com.salesianostriana.edu.apigestionrecetas.model.Ingrediente;
import com.salesianostriana.edu.apigestionrecetas.model.IngredienteReceta;
import com.salesianostriana.edu.apigestionrecetas.model.Receta;
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
    private final IngredienteRepository ingredienteRepository;
    private final IngredienteRecetaRepository ingredienteRecetaRepository;

    public List<Receta> findAll() {
        return recetaRepository.findAll();
    }

    public Receta findById(Long id) {
        return recetaRepository.findById(id)
                .orElseThrow(() -> new RecetaNotFoundException(id));
    }

    public Receta create(RecetaRequestDto dto) {
        if (recetaRepository.existsByNombre(dto.nombre())) {
            throw new RecetaYaCreadaException(dto.nombre());
        }

        return recetaRepository.save(dto.toEntity());
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

    public void deleteById(Long id) {
        Receta receta = findById(id);
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

    public IngredienteReceta toEntity (IngredienteReceta iR,Receta r, Ingrediente i) {
        return IngredienteReceta.builder()
                .cantidad(iR.getCantidad())
                .unidadMedida(iR.getUnidadMedida())
                .receta(r)
                .ingrediente(i)
                .build();
    }

}
