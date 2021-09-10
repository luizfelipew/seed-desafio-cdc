package com.deveficiente.cdc.novoautor;

public class FieldErrorOutPutDto {

    private String field;
    private String message;

    public FieldErrorOutPutDto() {
    }

    public FieldErrorOutPutDto(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}
