package br.com.luizalabs.wishlist.infra.error.resource;

public class ErrorResource {

    private String uri;
    private ErrorMessageWrapper errors;

    public ErrorResource(String uri, String errorMessage, Integer errorCode) {
        this.uri = uri;
        errors = new ErrorMessageWrapper();
        errors.addErrorMessage(errorMessage, errorCode);
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public ErrorMessageWrapper getErrors() {
        return errors;
    }

    public void setErrors(ErrorMessageWrapper errors) {
        this.errors = errors;
    }
}
