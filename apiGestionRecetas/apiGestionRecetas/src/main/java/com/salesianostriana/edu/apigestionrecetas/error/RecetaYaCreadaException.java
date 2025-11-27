package com.salesianostriana.edu.apigestionrecetas.error;

public class RecetaYaCreadaException extends RuntimeException {

    public RecetaYaCreadaException (String nombre) {
        super(String.format("No se puede crear '%s', ya hay una receta con ese nombre", nombre));

    }
}
