package com.salesianostriana.edu.apigestionrecetas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Receta {

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String nombre;
    private Integer tiempoPreparacionMin;
    private Dificultad dificultad;
    @OneToMany
    private Categoria categoria;
    @OneToMany
    private Set<Ingrediente> ingrediente;
}
