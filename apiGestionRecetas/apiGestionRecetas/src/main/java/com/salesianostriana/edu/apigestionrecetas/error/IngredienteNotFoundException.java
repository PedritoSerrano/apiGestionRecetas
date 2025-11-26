package com.salesianostriana.edu.apigestionrecetas.error;

public class IngredienteNotFoundException extends RuntimeException {
    public IngredienteNotFoundException(Long id) {
        super("No hay ningún ingrediente con este ID");
    }

    public IngredienteNotFoundException() {
        super("");
    }
}
