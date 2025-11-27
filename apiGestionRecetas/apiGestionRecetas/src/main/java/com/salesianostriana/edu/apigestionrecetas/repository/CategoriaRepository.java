package com.salesianostriana.edu.apigestionrecetas.repository;

import com.salesianostriana.edu.apigestionrecetas.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    boolean existsByNombre(String nombre);
    Optional<Categoria> findByNombre(String nombre);

}
