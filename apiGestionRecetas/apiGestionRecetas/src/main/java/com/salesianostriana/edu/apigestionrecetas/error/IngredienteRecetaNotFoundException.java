package com.salesianostriana.edu.apigestionrecetas.error;

public class IngredienteRecetaNotFoundException extends RuntimeException {
    public IngredienteRecetaNotFoundException(Long id) {
        super("No hay ningún ingredienteReceta con este ID");
    }

    public IngredienteRecetaNotFoundException() {
        super("");
    }
}
