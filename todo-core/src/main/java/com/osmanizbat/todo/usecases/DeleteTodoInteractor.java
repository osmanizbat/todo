package com.osmanizbat.todo.usecases;

import com.osmanizbat.todo.entities.Todo;
import com.osmanizbat.todo.boundary.RecordAlreadyExistsException;
import com.osmanizbat.todo.repositories.TodoRepository;
import com.osmanizbat.todo.boundary.TodoAlreadyExistsException;
import com.osmanizbat.todo.boundary.DeleteTodoRequest;
import com.osmanizbat.todo.boundary.DeleteTodoRequestBoundary;
import com.osmanizbat.todo.boundary.DeleteTodoResponse;
import com.osmanizbat.todo.boundary.DeleteTodoResponseBoundary;
import com.osmanizbat.todo.boundary.RecordDoesntExistException;
import com.osmanizbat.todo.boundary.TodoDoesntExistException;
import com.osmanizbat.todo.core.utils.TodoMapper;
import com.osmanizbat.todo.entities.TodoId;
import javax.inject.Inject;

/**
 *
 * @author osmanizbat
 */
public class DeleteTodoInteractor extends TodoInteractor
        implements DeleteTodoRequestBoundary {
    
    @Inject
    public DeleteTodoInteractor(TodoRepository todoRepository, TodoMapper mapper) {
        super(todoRepository, mapper);
    }

    public void invoke(DeleteTodoRequest request, DeleteTodoResponseBoundary responder) {
        tryDeleteTodo(request.id);
        responder.respond(new DeleteTodoResponse());
    }

    private void tryDeleteTodo(String id) {
        try {
            todoRepository.destroy(new TodoId(id));
        } catch (RecordDoesntExistException e) {
            throw new TodoDoesntExistException();
        }
    }

}
