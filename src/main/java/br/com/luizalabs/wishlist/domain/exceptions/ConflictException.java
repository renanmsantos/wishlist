package br.com.luizalabs.wishlist.domain.exceptions;

import br.com.luizalabs.wishlist.infra.error.ErrorEnum;

import java.net.URI;

public class ConflictException extends RuntimeException {

    private URI location;
    private ErrorEnum errorEnum;

    public ConflictException() { super(); }

    public ConflictException(String message ){ super(message); }

    public ConflictException(final ErrorEnum errorEnum, final URI location){
        super( errorEnum.getExceptionWordsMessage() );
        this.errorEnum = errorEnum;
        this.location = location;
    }

}
