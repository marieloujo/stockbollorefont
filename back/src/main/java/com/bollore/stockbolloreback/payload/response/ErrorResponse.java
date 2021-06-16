package com.bollore.stockbolloreback.payload.response;


public class ErrorResponse {

    private ErrorMessage errors;


    public ErrorResponse() {
    }

    public ErrorMessage getErrors() {
        return errors;
    }

    public void setErrors(ErrorMessage errors) {
        this.errors = errors;
    }


}
