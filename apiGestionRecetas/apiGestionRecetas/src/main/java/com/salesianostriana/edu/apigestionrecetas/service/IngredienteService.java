package com.salesianostriana.edu.apigestionrecetas.service;

import com.salesianostriana.edu.apigestionrecetas.dto.IngredienteRequestDto;
import com.salesianostriana.edu.apigestionrecetas.error.IngredienteNotFoundException;
import com.salesianostriana.edu.apigestionrecetas.error.IngredienteRecetaNotFoundException;
import com.salesianostriana.edu.apigestionrecetas.model.Ingrediente;
import com.salesianostriana.edu.apigestionrecetas.model.IngredienteReceta;
import com.salesianostriana.edu.apigestionrecetas.repository.IngredienteRecetaRepository;
import com.salesianostriana.edu.apigestionrecetas.repository.IngredienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredienteService {

    private final IngredienteRepository ingredienteRepository;
    private final IngredienteRecetaRepository ingredienteRecetaRepository;

    public List<Ingrediente> findAll () {
        return ingredienteRepository.findAll();
    }

    public Ingrediente findById (Long id) {
        return ingredienteRepository.findById(id)
                .orElseThrow(() -> new IngredienteNotFoundException(id));
    }

    public Ingrediente create(IngredienteRequestDto dto) {
        if (ingredienteRepository.existsByNombre(dto.nombre())) {
            throw new IllegalArgumentException("No puede haber dos ingredientes con el mismo nombre");
        }

        return ingredienteRepository.save(dto.toEntity());
    }

    public Ingrediente toEntity (IngredienteRequestDto dto, IngredienteReceta iR) {
        return Ingrediente.builder()
                .nombre(dto.nombre())
                .build();
    }

}
