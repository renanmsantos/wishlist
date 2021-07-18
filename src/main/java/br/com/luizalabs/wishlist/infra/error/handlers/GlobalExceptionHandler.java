package br.com.luizalabs.wishlist.infra.error.handlers;

import br.com.luizalabs.wishlist.infra.error.ErrorEnum;
import br.com.luizalabs.wishlist.infra.error.resource.ErrorResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResource> handleExceptions(Exception exception, HttpServletRequest request ){

        ResponseEntity<ErrorResource> responseEntity = Arrays.stream( ErrorEnum.values() )
                .filter( errorEnum -> exception.getMessage().contains( errorEnum.getExceptionWordsMessage() ) )
                .findFirst()
                .map( errorEnum -> ResponseEntity
                        .status( errorEnum.getStatusCode() )
                        .body( new ErrorResource( request.getRequestURI(), errorEnum.getErrorMessage(), errorEnum.getStatusCode().value() ) )
                )
                .orElse( ResponseEntity
                        .status( HttpStatus.INTERNAL_SERVER_ERROR.value() )
                        .body( new ErrorResource(request.getRequestURI(), exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()) ) );
        return responseEntity;
    }

}
