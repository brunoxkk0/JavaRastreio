package br.com.brunoxkk0.utils;

public class InvalidSroException extends Exception {
    public InvalidSroException(){
        System.out.println("Invalid SRO detected, please verify and try again!");
    }
}
