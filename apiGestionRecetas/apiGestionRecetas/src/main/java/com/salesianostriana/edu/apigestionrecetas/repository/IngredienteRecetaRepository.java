package com.salesianostriana.edu.apigestionrecetas.repository;

import com.salesianostriana.edu.apigestionrecetas.model.Ingrediente;
import com.salesianostriana.edu.apigestionrecetas.model.IngredienteReceta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IngredienteRecetaRepository extends JpaRepository<IngredienteReceta, Long> {

    Optional<IngredienteReceta> findByRecetaIdAndIngredienteId(Long recetaId, Long ingredienteId);
    boolean existsByRecetaIdAndIngredienteId(Long recetaId, Long ingredienteId);
    List<IngredienteReceta> findByIngrediente(Ingrediente ingrediente);

}
