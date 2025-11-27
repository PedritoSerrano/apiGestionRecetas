package com.salesianostriana.edu.apigestionrecetas.service;

import com.salesianostriana.edu.apigestionrecetas.dto.CategoriaRequestDto;
import com.salesianostriana.edu.apigestionrecetas.error.CategoriaNotFoundException;
import com.salesianostriana.edu.apigestionrecetas.error.NombreDuplicadoException;
import com.salesianostriana.edu.apigestionrecetas.model.Categoria;
import com.salesianostriana.edu.apigestionrecetas.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public List<Categoria> findAll () {
        return categoriaRepository.findAll();
    }

    public Categoria findById(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new CategoriaNotFoundException(id));
    }

    public Categoria create(CategoriaRequestDto dto) {
        if (categoriaRepository.existsByNombre(dto.nombre())) {
            throw new NombreDuplicadoException();
        }

        return categoriaRepository. save(dto.toEntity());
    }

    public Categoria update(Long id, CategoriaRequestDto dto) {
        Categoria categoriaExistente = findById(id);

        if (! categoriaExistente.getNombre().equals(dto.nombre())
                && categoriaRepository.existsByNombre(dto.nombre())) {
            throw new NombreDuplicadoException();
        }

        categoriaExistente.setNombre(dto.nombre());
        categoriaExistente.setDescripcion(dto.descripcion());

        return categoriaRepository.save(categoriaExistente);
    }

    public void deleteById(Long id) {
        Categoria categoria = findById(id);
        categoriaRepository. delete(categoria);
    }

}
