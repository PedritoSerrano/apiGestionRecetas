package com.salesianostriana.edu.apigestionrecetas.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;

@RestControllerAdvice
public class GlobalErrorController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ProblemDetail handleNotFoundException (EntityNotFoundException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
            HttpStatus.NOT_FOUND,
            ex.getMessage()
        );
        problemDetail.setTitle("Elemento no encontrado");
        problemDetail.setType(
                URI.create()
        );
        return problemDetail;
    }

    @ExceptionHandler(RecetaYaCreadaException.class)
    public ProblemDetail handleRecetaYaCreadaException (RecetaYaCreadaException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
            HttpStatus.CONFLICT,
            ex.getMessage()
        );
        problemDetail.setTitle("Receta ya existe");
        return problemDetail;
    }

    @ExceptionHandler(IngredienteYaAnadidoException.class)
    public ProblemDetail handleIngedienteYaAnadidoException (IngredienteYaAnadidoException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
            HttpStatus.CONFLICT,
            ex.getMessage()
        );
        problemDetail.setTitle("Ingrediente ya añadido");
        return problemDetail;
    }

    @ExceptionHandler(TiempoInvalidoException.class)
    public ProblemDetail handleTiempoInvalidoException (TiempoInvalidoException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
          HttpStatus.CONFLICT,
          ex.getMessage()
        );
        problemDetail.setTitle("Tiempo añadido inválido");
        return problemDetail;
    }
}
