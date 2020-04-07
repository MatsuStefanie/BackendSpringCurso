package com.cristal.stefanie.cursomc.services.exceptions;

public class DataIntregrityException extends RuntimeException {
    public DataIntregrityException(String msg){
        super(msg);
    }

    public DataIntregrityException(String msg, Throwable cause){
        super(msg, cause);
    }
}
