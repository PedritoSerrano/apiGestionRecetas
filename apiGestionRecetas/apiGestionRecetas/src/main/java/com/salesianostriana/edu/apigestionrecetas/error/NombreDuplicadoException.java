package com.salesianostriana.edu.apigestionrecetas.error;

public class NombreDuplicadoException extends RuntimeException {
    public NombreDuplicadoException(String message) {
        super(message);
    }
    public NombreDuplicadoException () {
        super("Ya existe una entidad con ese nombre");
    }
}
