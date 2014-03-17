package com.osmanizbat.todo.entities;

import lombok.Data;


/**
 *
 * @author osmanizbat
 */
@Data
public class Todo {
    
    private TodoId id;
    
    private String title;
    
    private boolean completed;

    public Todo() {
    }

    public Todo(String title) {
        this.title = title;
    }

    public Todo(TodoId id, String title, boolean completed) {
        this.id = id;
        this.title = title;
        this.completed = completed;
    }
    
    
}
