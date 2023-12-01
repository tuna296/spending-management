package com.learn.SpendingManagement.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Objects;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidationUsername.UsernameValidator.class)
public @interface ValidationUsername {

  String message() default "Invalid username";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  class UsernameValidator implements ConstraintValidator<ValidationUsername, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
      if (Objects.isNull(value) || value.isEmpty()) return true;
      return value.matches("^[a-zA-Z0-9]{5,}$");
    }
  }
}
