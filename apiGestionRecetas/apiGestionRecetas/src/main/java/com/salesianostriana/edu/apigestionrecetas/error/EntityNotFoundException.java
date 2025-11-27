package com.salesianostriana.edu.apigestionrecetas.error;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException (Long id) {
        super("Ya hay un ingrediente con ese id" + id);
    }

}
