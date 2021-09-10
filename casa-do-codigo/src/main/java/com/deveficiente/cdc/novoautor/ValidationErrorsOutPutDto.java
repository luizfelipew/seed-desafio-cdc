package com.deveficiente.cdc.novoautor;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorsOutPutDto {

    private List<String> globalErrorMessages = new ArrayList<>();
    private List<FieldErrorOutPutDto> fieldErrors = new ArrayList<>();

    public ValidationErrorsOutPutDto() {
    }

    public ValidationErrorsOutPutDto(List<String> globalErrorMessages, List<FieldErrorOutPutDto> fieldErrors) {
        this.globalErrorMessages = globalErrorMessages;
        this.fieldErrors = fieldErrors;
    }

    public void addError(String message) {
        globalErrorMessages.add(message);
    }

    public void addFieldError(String field, String message) {
        FieldErrorOutPutDto fieldError = new FieldErrorOutPutDto(field, message);
        fieldErrors.add(fieldError);
    }

    public List<String> getGlobalErrorMessages() {
        return globalErrorMessages;
    }

    public List<FieldErrorOutPutDto> getErrors() {
        return fieldErrors;
    }

    public int getNumberOfErrors() {
        return fieldErrors.size();
    }
}
