package br.com.brunoxkk0.utils;

public class InvalidRequestException extends Exception{

    public InvalidRequestException(String value, Exception exception){
        super(value, exception);
    }

    public InvalidRequestException(Exception exception){
        super(exception);
    }

}
