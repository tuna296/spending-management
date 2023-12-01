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

import static com.learn.SpendingManagement.constant.Constants.MessagesResponse.INVALID_EMAIL;

  @Target({ElementType.FIELD})
  @Retention(RetentionPolicy.RUNTIME)
  @Constraint(validatedBy = ValidationEmail.EmailValidator.class)
  public @interface ValidationEmail {

    String message() default INVALID_EMAIL;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


    class EmailValidator implements ConstraintValidator<ValidationEmail, String> {

      @Override
      public boolean isValid(String value, ConstraintValidatorContext context) {
        if (Objects.isNull(value) || value.isEmpty()) return true;
        return value.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
      }

    }
  }

