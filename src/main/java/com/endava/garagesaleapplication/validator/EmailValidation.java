package com.endava.garagesaleapplication.validator;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;

@Component
public final class EmailValidation {

    public static String checkEmailValidity(String email) {
        if (!EmailValidator.getInstance().isValid(email)) {
            throw new IllegalArgumentException("Invalid email format ");
        }

        return email;
    }
}