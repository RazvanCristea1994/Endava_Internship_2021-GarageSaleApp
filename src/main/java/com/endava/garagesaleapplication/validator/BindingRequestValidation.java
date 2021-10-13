package com.endava.garagesaleapplication.validator;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
public final class BindingRequestValidation {

    public static void check(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder stringBuilder = new StringBuilder();
            List<FieldError> errorList = bindingResult.getFieldErrors();
            errorList.forEach(errorField -> stringBuilder.append(errorField.getDefaultMessage()));

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, stringBuilder.toString(), new IllegalArgumentException());
        }
    }
}