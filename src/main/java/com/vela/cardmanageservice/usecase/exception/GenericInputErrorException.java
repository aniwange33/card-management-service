package com.vela.cardmanageservice.usecase.exception;

public class GenericInputErrorException extends RuntimeException {
     public GenericInputErrorException(String message) {
        super(message);
    }
}
