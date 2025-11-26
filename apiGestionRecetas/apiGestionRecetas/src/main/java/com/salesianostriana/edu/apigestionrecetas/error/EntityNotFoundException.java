package com.salesianostriana.edu.apigestionrecetas.error;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
