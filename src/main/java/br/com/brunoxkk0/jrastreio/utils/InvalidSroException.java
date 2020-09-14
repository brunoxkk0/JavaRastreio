package br.com.brunoxkk0.jrastreio.utils;

public class InvalidSroException extends Exception {

    public InvalidSroException(){
        super("Invalid SRO detected, please verify and try again!");
    }
}
