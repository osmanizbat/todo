package com.osmanizbat.todo.boundary;

import com.osmanizbat.todo.boundary.ResponseModel;
import lombok.Data;

/**
 *
 * @author osmanizbat
 */
@Data
public class TodoDTO implements ResponseModel {
    
    private String id;
    
    private String title;
    
    private boolean completed;

    public TodoDTO() {
    }
    
    public TodoDTO(String id, String title, boolean completed) {
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    
}
