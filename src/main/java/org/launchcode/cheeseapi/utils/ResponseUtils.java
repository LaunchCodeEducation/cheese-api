package org.launchcode.cheeseapi.utils;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponseUtils {
  private static Map<String, List<String>> buildFieldErrors(Errors errors) {
    Map<String, List<String>> fieldErrors = new HashMap<>();

    for (FieldError fieldError : errors.getFieldErrors()) {
      String fieldName = fieldError.getField();

      List<String> fieldValidationErrorMessages;
      if (fieldErrors.containsKey(fieldName)) {
        fieldValidationErrorMessages = fieldErrors.get(fieldName);
      } else {
        fieldValidationErrorMessages = new ArrayList<>();
      }

      fieldValidationErrorMessages.add(fieldError.getDefaultMessage());
      fieldErrors.put(fieldName, fieldValidationErrorMessages);
    }

    return fieldErrors;
  }

  public static ResponseEntity buildFieldErrorResponseEntity(Errors errors) {
    Map<String, Map<String, List<String>>> body = new HashMap<>();

    body.put("fieldErrors", buildFieldErrors(errors));

    return ResponseEntity.status(400).body(body);
  }
}
