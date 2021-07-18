package br.com.luizalabs.wishlist.infra.error;

import org.springframework.http.HttpStatus;

public enum ErrorEnum {

    NOT_FOUND( HttpStatus.NOT_FOUND.toString(), HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.toString() ),
    ALREADY_EXISTS( "Register already exists.", HttpStatus.CONFLICT, "ALREADY_EXISTS" );

    private String errorMessage;
    private HttpStatus statusCode;
    private String exceptionWordsMessage;

    ErrorEnum( String errorMessage, HttpStatus statusCode, String exceptionWordsMessage ){
        this.errorMessage = errorMessage;
        this.statusCode = statusCode;
        this.exceptionWordsMessage = exceptionWordsMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public String getExceptionWordsMessage() {
        return exceptionWordsMessage;
    }

    public void setExceptionWordsMessage(String exceptionWordsMessage) {
        this.exceptionWordsMessage = exceptionWordsMessage;
    }
}
