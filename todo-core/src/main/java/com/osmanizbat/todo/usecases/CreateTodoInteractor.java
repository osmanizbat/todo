package com.osmanizbat.todo.usecases;

import com.osmanizbat.todo.entities.Todo;
import com.osmanizbat.todo.boundary.RecordAlreadyExistsException;
import com.osmanizbat.todo.repositories.TodoRepository;
import com.osmanizbat.todo.boundary.TodoAlreadyExistsException;
import com.osmanizbat.todo.boundary.CreateTodoRequest;
import com.osmanizbat.todo.boundary.CreateTodoRequestBoundary;
import com.osmanizbat.todo.boundary.CreateTodoResponse;
import com.osmanizbat.todo.boundary.CreateTodoResponseBoundary;
import com.osmanizbat.todo.core.utils.TodoMapper;
import javax.inject.Inject;

/**
 *
 * @author osmanizbat
 */
public class CreateTodoInteractor extends TodoInteractor
        implements CreateTodoRequestBoundary {
    
    @Inject
    public CreateTodoInteractor(TodoRepository todoRepository, TodoMapper mapper) {
        super(todoRepository, mapper);
    }

    public void invoke(CreateTodoRequest inputModel, CreateTodoResponseBoundary responder) {
        Todo newTodo = createTodo(inputModel);
        tryPersistTodo(newTodo);
        responder.respond(createResponse(newTodo));
    }

    private Todo createTodo(CreateTodoRequest inputModel) {
        return new Todo(inputModel.title);
    }

    private void tryPersistTodo(Todo newTodo) {
        try {
            todoRepository.create(newTodo);
        } catch (RecordAlreadyExistsException e) {
            throw new TodoAlreadyExistsException();
        }
    }

    private CreateTodoResponse createResponse(Todo newTodo) {
        return new CreateTodoResponse(mapper.map(newTodo));
    }


}
