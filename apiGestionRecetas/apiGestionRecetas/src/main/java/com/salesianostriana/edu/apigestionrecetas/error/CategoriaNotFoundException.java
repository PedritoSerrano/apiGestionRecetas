package com.salesianostriana.edu.apigestionrecetas.error;

public class CategoriaNotFoundException extends RuntimeException {
    public CategoriaNotFoundException(Long id) {
        super("No hay ninguna categoría con este ID");
    }
    public CategoriaNotFoundException () {
        super("");
    }
}
