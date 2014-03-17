package com.osmanizbat.todo.data.utils;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.osmanizbat.todo.entities.Todo;
import com.osmanizbat.todo.entities.TodoId;
import com.osmanizbat.todo.data.models.TodoRow;
import java.util.List;

/**
 *
 * @author osmanizbat
 */
public class TodoMapper {
    
    private Function<Todo, TodoRow> todoToRowTransformer
            = new Function<Todo, TodoRow>() {
                public TodoRow apply(Todo todo) {
                    String id = todo.getId() != null ? todo.getId().id : null;
                    return new TodoRow(id, todo.getTitle(), todo.isCompleted());
                }
            };
    
    private Function<TodoRow, Todo> rowToTodoTransformer
            = new Function<TodoRow, Todo>() {
                public Todo apply(TodoRow dto) {
                    return new Todo(new TodoId(dto.id), dto.title, dto.completed);
                }
            };
    
    public TodoRow map(Todo todo){
        return todoToRowTransformer.apply(todo);
    }
    
    public Todo map(TodoRow dto){
        return rowToTodoTransformer.apply(dto);
    }
    
    public List<TodoRow> mapToDTO(List<Todo> todos){
        return Lists.transform(todos, todoToRowTransformer);
    }
    
    public List<Todo> mapToTodo(List<TodoRow> dtos){
        return Lists.transform(dtos, rowToTodoTransformer);
    }
    
    
    
}
