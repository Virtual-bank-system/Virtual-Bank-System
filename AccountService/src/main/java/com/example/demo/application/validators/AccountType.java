package com.example.demo.application.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Pattern;

import java.lang.annotation.*;

@Constraint(validatedBy = {})
@Target({ElementType.LOCAL_VARIABLE, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Pattern(regexp = "DEBIT|CREDIT|debit|credit", message = "Account Type must be either DEBIT or CREDIT")

public @interface AccountType {
    String message() default "Invalid account type";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

