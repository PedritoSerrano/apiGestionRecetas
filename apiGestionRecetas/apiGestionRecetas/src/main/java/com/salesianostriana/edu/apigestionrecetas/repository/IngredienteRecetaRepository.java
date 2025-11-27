package com.salesianostriana.edu.apigestionrecetas.repository;

import com.salesianostriana.edu.apigestionrecetas.model.IngredienteReceta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredienteRecetaRepository extends JpaRepository<IngredienteReceta, Long> {


    boolean existsByRecetaIdAndIngredienteId(Long recetaId, Long ingredienteId);

}
