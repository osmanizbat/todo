package com.osmanizbat.todo.boundary;


/**
 *
 * @author osmanizbat
 */
public class CompleteTodoResponse implements ResponseModel {
    
    public final TodoDTO dto;

    public CompleteTodoResponse(TodoDTO dto) {
        this.dto = dto;
    }


}
