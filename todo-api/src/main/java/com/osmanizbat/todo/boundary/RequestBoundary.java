package com.osmanizbat.todo.boundary;

/**
 *
 * @author osmanizbat
 */
public interface RequestBoundary<RequestModel, ResponseBoundary> {
    
    void invoke(RequestModel inputModel, ResponseBoundary responseModel);    
    
}
