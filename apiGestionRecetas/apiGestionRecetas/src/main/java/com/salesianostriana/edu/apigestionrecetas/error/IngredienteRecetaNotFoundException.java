package com.salesianostriana.edu.apigestionrecetas.error;

public class IngredienteRecetaNotFoundException extends EntityNotFoundException {
    public IngredienteRecetaNotFoundException (String message) {
        super(message);
    }

    public IngredienteRecetaNotFoundException(Long id) {
        super("No hay ningún ingredienteReceta con este ID: %id".formatted(id));
    }

    public IngredienteRecetaNotFoundException() {
        super("No hay ningún ingredienteReceta con esos criterios de búsqueda");
    }
}
