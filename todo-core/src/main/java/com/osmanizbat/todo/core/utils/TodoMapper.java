package com.osmanizbat.todo.core.utils;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.osmanizbat.todo.boundary.TodoDTO;
import com.osmanizbat.todo.entities.Todo;
import com.osmanizbat.todo.entities.TodoId;
import java.util.List;

/**
 *
 * @author osmanizbat
 */
public class TodoMapper {
    
    private Function<Todo, TodoDTO> todoToDTOMapper
            = new Function<Todo, TodoDTO>() {
                public TodoDTO apply(Todo todo) {
                    String id = todo.getId() != null ? todo.getId().id : null;
                    return new TodoDTO(id, todo.getTitle(), todo.isCompleted());
                }
            };
    
    private Function<TodoDTO, Todo> dtoToTodoMapper
            = new Function<TodoDTO, Todo>() {
                public Todo apply(TodoDTO dto) {
                    return new Todo(new TodoId(dto.getId()), dto.getTitle(), dto.isCompleted());
                }
            };
    
    public TodoDTO map(Todo todo){
        return todoToDTOMapper.apply(todo);
    }
    
    public Todo map(TodoDTO dto){
        return dtoToTodoMapper.apply(dto);
    }
    
    public List<TodoDTO> mapToDTO(List<Todo> todos){
        return Lists.transform(todos, todoToDTOMapper);
    }
    
    public List<Todo> mapToTodo(List<TodoDTO> dtos){
        return Lists.transform(dtos, dtoToTodoMapper);
    }
    
    
    
}
