package com.osmanizbat.todo.boundary;


/**
 *
 * @author osmanizbat
 */
public class CreateTodoRequest implements RequestModel {

    public String title;

    public CreateTodoRequest(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    

}
