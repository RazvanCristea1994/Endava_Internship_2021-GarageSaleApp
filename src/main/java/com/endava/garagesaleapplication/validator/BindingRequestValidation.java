package com.endava.garagesaleapplication.validator;

import com.endava.garagesaleapplication.controller.exception.customexception.ConstraintsNotRespected;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

@Component
public final class BindingRequestValidation {

    public static void check(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder stringBuilder = new StringBuilder();
            List<FieldError> errorList = bindingResult.getFieldErrors();
            errorList.forEach(errorField -> stringBuilder.append(errorField.getDefaultMessage()));

            throw new ConstraintsNotRespected(stringBuilder.toString());
        }
    }
}