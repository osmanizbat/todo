package com.osmanizbat.todo.boundary;

/**
 *
 * @author osmanizbat
 */
public class RecordDoesntExistException extends RuntimeException {
    
    public RecordDoesntExistException(String message){
        super(message) ;
    }

    public RecordDoesntExistException() {}    
    
}
