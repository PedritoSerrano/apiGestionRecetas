package com.salesianostriana.edu.apigestionrecetas.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Categoria {

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String nombre;
    private String descripcion;
    @OneToMany(mappedBy = "categoria")
    private Set<Receta> receta;

}
