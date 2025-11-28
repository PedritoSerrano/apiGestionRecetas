package com.salesianostriana.edu.apigestionrecetas.error;

public class RecetaNotFoundException extends EntityNotFoundException {
    public RecetaNotFoundException (String message) {
        super(message);
    }

    public RecetaNotFoundException(Long id) {
        super("No hay ninguna receta con este ID: %d".formatted(id));
    }

    public RecetaNotFoundException() {
        super("No hay ninguna receta con esos criterios de búsqueda");
    }
}
