package com.salesianostriana.edu.apigestionrecetas.error;

public class IngredienteNotFoundException extends EntityNotFoundException {
    public IngredienteNotFoundException (String message) {super(message);}

    public IngredienteNotFoundException(Long id) {
        super("No hay ningún ingrediente con este ID: %d".formatted(id));
    }

    public IngredienteNotFoundException() {
        super("No hay ningún ingredientes con esos criterios de búsqueda");
    }
}
