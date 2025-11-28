package com.salesianostriana.edu.apigestionrecetas.error;

public class CategoriaNotFoundException extends EntityNotFoundException {
    public CategoriaNotFoundException (String message) {
        super(message);
    }

    public CategoriaNotFoundException(Long id) {
        super("No hay ninguna categoria con este ID: %d".formatted(id));
    }
    public CategoriaNotFoundException () {
        super("No hay ninguna categoria con esos criterios de búsqueda");
    }
}
