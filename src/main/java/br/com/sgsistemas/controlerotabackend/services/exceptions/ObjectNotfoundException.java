package br.com.sgsistemas.controlerotabackend.services.exceptions;

public class ObjectNotfoundException extends RuntimeException{

    public ObjectNotfoundException(String message) {
        super(message);
    }

    public ObjectNotfoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
