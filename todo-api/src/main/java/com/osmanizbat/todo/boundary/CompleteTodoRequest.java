package com.osmanizbat.todo.boundary;

import com.osmanizbat.todo.boundary.RequestModel;

/**
 *
 * @author osmanizbat
 */
public class CompleteTodoRequest implements RequestModel {

    public final String id;

    public CompleteTodoRequest(String id) {
        this.id = id;
    }

}
