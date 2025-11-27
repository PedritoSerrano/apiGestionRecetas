package com.salesianostriana.edu.apigestionrecetas.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
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

    @OneToMany(mappedBy = "ingrediente", fetch = FetchType.EAGER)
    @Builder.Default
    private List<IngredienteReceta> ingredienteRecetas = new ArrayList<>();

}
