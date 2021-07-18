package br.com.luizalabs.wishlist.domain.exceptions;

import br.com.luizalabs.wishlist.infra.error.ErrorEnum;

public class NotFoundException extends RuntimeException {

    public NotFoundException() { super(); }

    public NotFoundException( String message ){ super(message); }

    public NotFoundException(ErrorEnum errorEnum){
        super( errorEnum.getExceptionWordsMessage() );
    }

}
