package com.salesianostriana.edu.apigestionrecetas.repository;

import com.salesianostriana.edu.apigestionrecetas.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
