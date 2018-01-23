package com.calculator.exception;

/**
 * Exception that will be thrown if an expression contains errors or cannot be parsed
 */
public class ParseException extends Exception {

    public ParseException(String message){
        super(message);
    }

}
