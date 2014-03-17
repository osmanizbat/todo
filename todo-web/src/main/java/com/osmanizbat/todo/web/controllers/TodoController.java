package com.osmanizbat.todo.web.controllers;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.osmanizbat.todo.boundary.*;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import javax.inject.Inject;
import lombok.Getter;

/**
 *
 * @author osmanizbat
 */
@ManagedBean
@ViewScoped
public class TodoController implements  CreateTodoResponseBoundary
                                        , ListTodosResponseBoundary 
                                        , CompleteTodoResponseBoundary
                                        , DeleteTodoResponseBoundary {
    
    @Inject
    private CreateTodoRequestBoundary create;   
    
    @Inject
    private ListTodosRequestBoundary list;   
    
    @Inject
    private CompleteTodoRequestBoundary complete;   
    
    @Inject
    private DeleteTodoRequestBoundary delete;   
    
    @Getter
    private CreateTodoRequest createTodoRequest = new CreateTodoRequest(null);

    private List<TodoDTO> todos;
    
    public List<TodoDTO> getTodos() {
        if(todos == null){
            list.invoke(new ListTodosRequest(), this);
        }
        
        return todos;
    }
    
    public void create() {
        try {
            create.invoke(createTodoRequest, this);
        } catch (TodoAlreadyExistsException ex) {
            ex.printStackTrace();
        }
    }

    public void complete(TodoDTO todo) {
        complete.invoke(new CompleteTodoRequest(todo.getId()), this);
    }
    
    public void delete(TodoDTO todo) {
        try {
            delete.invoke(new DeleteTodoRequest(todo.getId()), this);
            deleteFromTodoList(todo);
        } catch (TodoDoesntExistException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public void respond(CreateTodoResponse model) {
        todos.add(0, model.dto);
        createTodoRequest.title = null;
    }

    @Override
    public void respond(ListTodosResponse model) {
        todos = Lists.newArrayList(model.todos);
    }

    @Override
    public void respond(CompleteTodoResponse model) {
        updateToTodoList(model.dto);
    }

    private void updateToTodoList(TodoDTO dto) {
        final ListIterator<TodoDTO> listIterator = todos.listIterator();
        
        while(listIterator.hasNext()){
            if(dto.equals(listIterator.next())){
                listIterator.set(dto);
            }
        }
        
    }

    private void deleteFromTodoList(TodoDTO dto) {
        final ListIterator<TodoDTO> listIterator = todos.listIterator();
        
        while(listIterator.hasNext()){
            if(dto.equals(listIterator.next())){
                listIterator.remove();
            }
        }
        
    }

    @Override
    public void respond(DeleteTodoResponse model) {
        
    }

    
    
}
