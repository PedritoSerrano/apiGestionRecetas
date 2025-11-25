package com.salesianostriana.edu.apigestionrecetas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Ingrediente {

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String nombre;
    @ManyToMany(mappedBy = "recetas")
    private List<Receta> receta;

}
