package com.salesianostriana.edu.apigestionrecetas.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.util.List;

@RestControllerAdvice
public class GlobalErrorController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ProblemDetail handleNotFoundException(EntityNotFoundException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                ex.getMessage()
        );
        problemDetail.setTitle("Elemento no encontrado");
        problemDetail.setType(
                URI.create("https://api.gestionrecetas.com/errors/not-found")
        );
        return problemDetail;
    }

    @ExceptionHandler(RecetaYaCreadaException.class)
    public ProblemDetail handleRecetaYaCreadaException(RecetaYaCreadaException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.CONFLICT,
                ex.getMessage()
        );
        problemDetail.setTitle("Receta ya existe");
        problemDetail.setType(
                URI.create("https://api.gestionrecetas.com/errors/receta-duplicada")
        );
        return problemDetail;
    }

    @ExceptionHandler(IngredienteYaAnadidoException.class)
    public ProblemDetail handleIngedienteYaAnadidoException(IngredienteYaAnadidoException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.CONFLICT,
                ex.getMessage()
        );
        problemDetail.setTitle("Ingrediente ya añadido");
        problemDetail.setType(
                URI.create("https://api.gestionrecetas.com/errors/ingrediente-existente")
        );
        return problemDetail;
    }

    @ExceptionHandler(TiempoInvalidoException.class)
    public ProblemDetail handleTiempoInvalidoException(TiempoInvalidoException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.CONFLICT,
                ex.getMessage()
        );
        problemDetail.setTitle("Tiempo añadido inválido");
        problemDetail.setType(
                URI.create("https://api.gestionrecetas.com/errors/tiempo-invalido")
        );
        return problemDetail;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        ProblemDetail result = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Error de validación");

        List<ApiValidationSubError> subErrors =
                ex.getAllErrors().stream()
                        .map(ApiValidationSubError::from)
                        .toList();

        result.setProperty("invalid-params", subErrors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(result);
    }


    @Builder
    record ApiValidationSubError(
            String Object,
            String message,
            @JsonInclude(JsonInclude.Include.NON_NULL)
            String field,
            @JsonInclude(JsonInclude.Include.NON_NULL)
            Object rejectedValue
    ) {
        public ApiValidationSubError(String object, String message) {
            this(object, message, null, null);
        }

        public static ApiValidationSubError from(ObjectError error) {
            ApiValidationSubError result = null;

            if (error instanceof FieldError fieldError) {
                result = ApiValidationSubError.builder()
                        .Object(error.getObjectName())
                        .message(error.getDefaultMessage())
                        .field(fieldError.getField())
                        .rejectedValue(fieldError.getRejectedValue())
                        .build();
            } else {
                result = ApiValidationSubError.builder()
                        .Object(error.getObjectName())
                        .message(error.getDefaultMessage())
                        .build();
            }
            return result;
        }

    }
}