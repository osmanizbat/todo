package com.osmanizbat.todo.bootstrap;

/**
 *
 * @author osmanizbat
 */
public class DataModuleNotFoundException extends RuntimeException {
    
    public DataModuleNotFoundException(String message){
        super(message) ;
    }

    public DataModuleNotFoundException() {}    
    
}

