package com.prabhatxmishra.autolife.ExceptionHandling;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String message)
    {
        super(message);
    }
}
