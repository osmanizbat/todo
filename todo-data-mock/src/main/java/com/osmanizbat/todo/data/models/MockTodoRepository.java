package com.osmanizbat.todo.data.models;

import static com.google.common.collect.Lists.newArrayList;
import com.google.common.collect.Maps;
import com.osmanizbat.todo.entities.Todo;
import com.osmanizbat.todo.boundary.RecordAlreadyExistsException;
import com.osmanizbat.todo.boundary.RecordDoesntExistException;
import com.osmanizbat.todo.entities.TodoId;
import com.osmanizbat.todo.repositories.TodoRepository;
import com.osmanizbat.todo.data.utils.TodoMapper;
import java.util.List;
import java.util.Map;

/**
 *
 * @author osmanizbat
 */
public class MockTodoRepository implements TodoRepository {
    
    private TodoMapper mapper = new TodoMapper();

    private Map<String, TodoRow> records = Maps.newConcurrentMap();

    public MockTodoRepository() {
        create(new Todo("Test todo 1"));
        create(new Todo("Test todo 2"));
    }

    public Todo get(TodoId todoId) {
        TodoRow row = records.get(todoId.id);
        return row != null ? mapper.map(row) : null;
    }

    public void update(Todo entity) {
        validateExists(entity.getId());
        updateExisting(entity);
    }

    public void destroy(TodoId todoId) {
        validateExists(todoId);
        destroyRecord(todoId);
    }

    public void create(Todo todo) {
        TodoRow todoRow = createTodoRow(todo);
        addToStorage(todoRow);
        todo.setId(new TodoId(todoRow.id));
    }

    public List<Todo> all() {
        return mapper.mapToTodo(newArrayList(records.values()));
    }

    private void updateExisting(Todo todo) {
        TodoRow row = mapper.map(todo);
        records.put(row.id, row);
    }

    private void validateExists(TodoId todoId) {
        if (!records.containsKey(todoId.id)) {
            throw new RecordDoesntExistException();
        }
    }

    private void destroyRecord(TodoId todoId) {
        TodoRow record = records.get(todoId.id);
        records.remove(todoId.id);
    }

    private void addToStorage(TodoRow todoRow) {
        validateDoesntExistYet(todoRow.id);
        records.put(todoRow.id, todoRow);
    }

    private TodoRow createTodoRow(Todo todo) {
        TodoId id = TodoId.newId();
        return new TodoRow(id.id, todo.getTitle(), false);
    }

    void validateDoesntExistYet(String todoId) {
        if (records.containsKey(todoId)) {
            throw new RecordAlreadyExistsException();
        }
    }

}
