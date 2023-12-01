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

import static com.learn.SpendingManagement.constant.Constants.MessagesResponse.INVALID_PHONE_NUMBER;


@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidationPhoneNumber.PhoneNumberValidator.class)
public @interface ValidationPhoneNumber {

  String message() default INVALID_PHONE_NUMBER;

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};


  class PhoneNumberValidator implements ConstraintValidator<ValidationPhoneNumber, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
      if (Objects.isNull(value) || value.isEmpty()) return true;
      return value.matches("(84|0[3|5|7|8|9])+([0-9]{8})\\b");
    }
  }

}