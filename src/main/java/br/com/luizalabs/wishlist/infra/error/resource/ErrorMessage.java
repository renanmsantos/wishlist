package br.com.luizalabs.wishlist.infra.error.resource;

public class ErrorMessage {

    private String message;
    private Integer code;

    public ErrorMessage(String errorMessage, Integer errorCode) {
        this.message = errorMessage;
        this.code = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
