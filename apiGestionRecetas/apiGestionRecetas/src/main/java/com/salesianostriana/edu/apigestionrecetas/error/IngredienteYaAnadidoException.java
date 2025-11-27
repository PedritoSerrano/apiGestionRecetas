package com.salesianostriana.edu.apigestionrecetas.error;

public class IngredienteYaAnadidoException extends RuntimeException {
    public IngredienteYaAnadidoException(String nombreIngrediente, String nombreReceta) {
        super(String.format("El ingrediente '%s' ya está añadido a la receta '%s'", nombreIngrediente, nombreReceta));
    }
}