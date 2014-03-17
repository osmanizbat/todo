package com.osmanizbat.todo.data.models;

import lombok.Data;

/**
 *
 * @author osmanizbat
 */
public class TodoRow {
    
    public final String id;
    
    public final String title;
    
    public final boolean completed;

    public TodoRow(String id, String title) {
        this.id = id;
        this.title = title;
        this.completed = false;
    }

    public TodoRow(String id, String title, boolean completed) {
        this.id = id;
        this.title = title;
        this.completed = completed;
    }
    
    
}
