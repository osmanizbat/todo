package com.osmanizbat.todo.boundary;

/**
 *
 * @author osmanizbat
 */
public interface ResponseBoundary<ResponseModel> {
    
    void respond(ResponseModel model);    
    
}
