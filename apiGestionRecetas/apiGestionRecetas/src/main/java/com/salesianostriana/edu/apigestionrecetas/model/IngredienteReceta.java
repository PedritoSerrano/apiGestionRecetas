package com.salesianostriana.edu.apigestionrecetas.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class IngredienteReceta {

    @Id
    @GeneratedValue
    private Long id;

    private double cantidad;
    private String unidadMedida;

    @ManyToOne
    @JoinColumn(name = "receta_id")
    private Receta receta;

    @ManyToOne
    @JoinColumn(name = "ingrediente_id")
    private Ingrediente ingrediente;
}
