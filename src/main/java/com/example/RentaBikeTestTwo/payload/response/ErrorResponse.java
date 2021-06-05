package com.example.RentaBikeTestTwo.payload.response;

import java.util.HashMap;
import java.util.Map;

public class ErrorResponse {
    public Map<String, String> errors;

    public ErrorResponse() {
        this.errors = new HashMap<>();
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
    public void addError(String errorType, String errorMessage){
        errors.put(errorType, errorMessage);
    }
    public boolean hasErrors() {
        return errors.size() > 0;
    }
}
