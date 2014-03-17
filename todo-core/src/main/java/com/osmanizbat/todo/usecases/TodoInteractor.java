package com.osmanizbat.todo.usecases;

import com.osmanizbat.todo.boundary.TodoDoesntExistException;
import com.osmanizbat.todo.core.utils.TodoMapper;
import com.osmanizbat.todo.entities.Todo;
import com.osmanizbat.todo.entities.TodoId;
import com.osmanizbat.todo.repositories.TodoRepository;

/**
 *
 * @author osmanizbat
 */
public abstract class TodoInteractor {

    protected final TodoRepository todoRepository;
    
    protected final TodoMapper mapper;

    protected TodoInteractor(TodoRepository todoRepository, TodoMapper mapper) {
        this.todoRepository = todoRepository;
        this.mapper = mapper;
    }

    protected Todo getExistingTodo(String todoId) {
        Todo todo = todoRepository.get(new TodoId(todoId));
        confirmTodoExists(todo);
        return todo;
    }

    static void confirmTodoExists(Todo todo) {
        if (todo == null) {
            throw new TodoDoesntExistException();
        }
    }

}
