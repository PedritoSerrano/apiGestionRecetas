package com.salesianostriana.edu.apigestionrecetas.error;

public class CategoriaNotFoundException extends EntityNotFoundException {
    public CategoriaNotFoundException (String message) {
        super(message);
    }

    public CategoriaNotFoundException(Long id) {
        super("No hay ninguna categoría con este ID: %id".formatted(id));
    }
    public CategoriaNotFoundException () {
        super("No hay ninguna categoría con esos criterios de búsqueda");
    }
}
