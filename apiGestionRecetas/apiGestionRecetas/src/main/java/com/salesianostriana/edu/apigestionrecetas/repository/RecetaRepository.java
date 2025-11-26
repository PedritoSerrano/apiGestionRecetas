package com.salesianostriana.edu.apigestionrecetas.repository;

import com.salesianostriana.edu.apigestionrecetas.model.Receta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecetaRepository extends JpaRepository<Receta, Long> {
}
