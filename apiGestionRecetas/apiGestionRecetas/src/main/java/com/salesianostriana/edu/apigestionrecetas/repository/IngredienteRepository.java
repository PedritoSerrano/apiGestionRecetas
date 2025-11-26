package com.salesianostriana.edu.apigestionrecetas.repository;

import com.salesianostriana.edu.apigestionrecetas.model.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {
}
