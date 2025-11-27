package com.salesianostriana.edu.apigestionrecetas.error;

public class TiempoInvalidoException extends RuntimeException {
    public TiempoInvalidoException(Integer tiempo) {
        super(String. format("El tiempo de preparación debe ser mayor a 0.  Valor recibido: %d minutos", tiempo));
    }

    public TiempoInvalidoException() {
        super("El tiempo de preparación debe ser mayor a 0");
    }
}