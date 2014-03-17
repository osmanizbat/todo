package com.osmanizbat.todo.boundary;

import com.osmanizbat.todo.boundary.ResponseModel;
import com.osmanizbat.todo.boundary.TodoDTO;
import java.util.List;

/**
 *
 * @author osmanizbat
 */
public class ListTodosResponse implements ResponseModel {
    
    public final List<TodoDTO> todos;   

    public ListTodosResponse(List<TodoDTO> todos) {
        this.todos = todos;
    }
    
}
