package com.salesianostriana.edu.apigestionrecetas.validation;

import ch.qos.logback.core.util.StringUtil;
import com.salesianostriana.edu.apigestionrecetas.repository.RecetaRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

public class UniqueNombreValidator implements ConstraintValidator<UniqueNombre, String> {

    @Autowired
    private RecetaRepository repository;

    @Override
    public boolean isValid(String nombre, ConstraintValidatorContext context) {
        return StringUtils.hasText(nombre) && !repository.existsByNombre(nombre);
    }
}
