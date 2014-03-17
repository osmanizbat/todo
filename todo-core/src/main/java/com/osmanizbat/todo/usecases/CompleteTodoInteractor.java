package com.osmanizbat.todo.usecases;

import com.osmanizbat.todo.boundary.CompleteTodoRequest;
import com.osmanizbat.todo.boundary.CompleteTodoRequestBoundary;
import com.osmanizbat.todo.boundary.CompleteTodoResponse;
import com.osmanizbat.todo.boundary.CompleteTodoResponseBoundary;
import com.osmanizbat.todo.boundary.RecordAlreadyExistsException;
import com.osmanizbat.todo.boundary.RecordDoesntExistException;
import com.osmanizbat.todo.boundary.TodoAlreadyExistsException;
import com.osmanizbat.todo.boundary.TodoDoesntExistException;
import com.osmanizbat.todo.core.utils.TodoMapper;
import com.osmanizbat.todo.entities.Todo;
import com.osmanizbat.todo.repositories.TodoRepository;
import javax.inject.Inject;

/**
 *
 * @author osmanizbat
 */
public class CompleteTodoInteractor extends TodoInteractor
        implements CompleteTodoRequestBoundary {

    @Inject
    public CompleteTodoInteractor(TodoRepository todoRepository, TodoMapper mapper) {
        super(todoRepository, mapper);
    }

    public void invoke(CompleteTodoRequest request, CompleteTodoResponseBoundary responder) {
        Todo todo = getExistingTodo(request.id);
        completeTodo(todo);
        responder.respond(createResponse(todo));
    }
    
    private void completeTodo(Todo todo) {
        try {
            todo.setCompleted(true);
            todoRepository.update(todo);
        } catch (RecordDoesntExistException e) {
            throw new TodoDoesntExistException();
        }
    }

    private CompleteTodoResponse createResponse(Todo todo) {
        return new CompleteTodoResponse(mapper.map(todo));
    }


}
