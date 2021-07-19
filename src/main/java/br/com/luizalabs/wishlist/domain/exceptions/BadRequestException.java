package br.com.luizalabs.wishlist.domain.exceptions;

import br.com.luizalabs.wishlist.infra.error.ErrorEnum;

public class BadRequestException extends RuntimeException {

    public BadRequestException() { super(); }

    public BadRequestException(String message ){ super(message); }

    public BadRequestException(ErrorEnum errorEnum){
        super( errorEnum.getExceptionWordsMessage() );
    }

}
