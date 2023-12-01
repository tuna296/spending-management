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
@Constraint(validatedBy = ValidationPassword.PasswordValidator.class)
public @interface ValidationPassword {

  String message() default "Invalid password";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  class PasswordValidator implements ConstraintValidator<ValidationPassword, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
      if (Objects.isNull(value) || value.isEmpty()) return true;
      return value.length() >= 8;
    }
  }
}
