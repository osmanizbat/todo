package com.osmanizbat.todo.entities;

import java.util.UUID;

/**
 *
 * @author osmanizbat
 */
public class TodoId {
    
    public final String id;

    public TodoId(String id) {
        this.id = id;
    }
    
    public static TodoId newId(){
        return new TodoId(UUID.randomUUID().toString());
    }
    
}
