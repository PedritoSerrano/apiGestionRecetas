package com.salesianostriana.edu.apigestionrecetas.error;

public class RecetaNotFoundException extends RuntimeException {
    public RecetaNotFoundException(Long id) {
        super("No hay ninguna receta con este ID");
    }

    public RecetaNotFoundException() {
        super("");
    }
}
