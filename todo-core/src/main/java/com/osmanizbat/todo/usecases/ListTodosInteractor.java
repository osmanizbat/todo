package com.osmanizbat.todo.usecases;

import com.osmanizbat.todo.repositories.TodoRepository;
import com.osmanizbat.todo.boundary.TodoDTO;
import com.osmanizbat.todo.boundary.ListTodosRequest;
import com.osmanizbat.todo.boundary.ListTodosRequestBoundary;
import com.osmanizbat.todo.boundary.ListTodosResponse;
import com.osmanizbat.todo.boundary.ListTodosResponseBoundary;
import com.osmanizbat.todo.core.utils.TodoMapper;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author osmanizbat
 */
public class ListTodosInteractor extends TodoInteractor implements ListTodosRequestBoundary {

    private TodoMapper mapper = new TodoMapper();

    @Inject
    public ListTodosInteractor(TodoRepository todoRepository, TodoMapper mapper) {
        super(todoRepository, mapper);
    }

    public void invoke(ListTodosRequest request, ListTodosResponseBoundary responder) {
        responder.respond(createResponse());
    }

    private ListTodosResponse createResponse() {
        return new ListTodosResponse(getTodos());
    }

    private List<TodoDTO> getTodos() {
        return mapper.mapToDTO(todoRepository.all());
    }
}

