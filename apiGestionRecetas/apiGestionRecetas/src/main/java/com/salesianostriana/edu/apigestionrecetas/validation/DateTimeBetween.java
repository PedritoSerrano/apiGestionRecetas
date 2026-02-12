package com.salesianostriana.edu.apigestionrecetas.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;
import java.time.LocalDateTime;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateTimeBetweenValidator.class)
@Documented
public @interface DateTimeBetween {

    String message() default "La fecha de creación debe ser anterior a la fecha de última actualización";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String min();
    String max();

}
