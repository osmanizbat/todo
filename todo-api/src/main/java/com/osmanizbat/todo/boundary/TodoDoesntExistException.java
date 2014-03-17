package com.osmanizbat.todo.boundary;

import com.osmanizbat.todo.boundary.RecordDoesntExistException;


/**
 *
 * @author osmanizbat
 */
public class TodoDoesntExistException extends RecordDoesntExistException {

    public TodoDoesntExistException() {
    }

    public TodoDoesntExistException(String message) {
        super(message);
    }
}
