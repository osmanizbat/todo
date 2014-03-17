package com.osmanizbat.todo.boundary;


/**
 *
 * @author osmanizbat
 */
public class DeleteTodoRequest implements RequestModel {

    public final String id;

    public DeleteTodoRequest(String id) {
        this.id = id;
    }

}
