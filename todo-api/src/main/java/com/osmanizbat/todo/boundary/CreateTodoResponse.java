package com.osmanizbat.todo.boundary;


/**
 *
 * @author osmanizbat
 */
public class CreateTodoResponse implements ResponseModel {
    
    public final TodoDTO dto;

    public CreateTodoResponse(TodoDTO dto) {
        this.dto = dto;
    }
}
