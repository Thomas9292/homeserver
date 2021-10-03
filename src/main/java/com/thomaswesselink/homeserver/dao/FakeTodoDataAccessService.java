package com.thomaswesselink.homeserver.dao;

import com.thomaswesselink.homeserver.model.Todo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class FakeTodoDataAccessService implements TodoDao {

    private static final List<Todo> DB = new ArrayList<>();

    @Override
    public int insertTodo(UUID id, Todo todo) {
        DB.add(new Todo(id,
                todo.getTitle(),
                todo.getDescription(),
                todo.isDone(),
                todo.getDateCreated()));
        return 1;
    }

    @Override
    public List<Todo> selectAllTodos() {
        return DB;
    }

    @Override
    public Optional<Todo> selectTodoById(UUID id) {
        return DB.stream()
                .filter(todo -> todo.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deleteTodoById(UUID id) {
        Optional<Todo> todoMaybe = selectTodoById(id);
        if (todoMaybe.isPresent()) {
            DB.remove(todoMaybe.get());
            return 1;
        }
        return 0;
    }

    @Override
    public int updateTodoById(UUID id, Todo todo) {
        return selectTodoById(id)
                .map(t -> {
                    int indexOfTodoToUpdate = DB.indexOf(t);
                    if (indexOfTodoToUpdate >= 0) {
                        Todo updatedTodo = new Todo(id,
                                todo.getTitle(),
                                todo.getDescription(),
                                todo.isDone(),
                                t.getDateCreated());
                        DB.set(indexOfTodoToUpdate, updatedTodo);
                        return 1;
                    }
                    return 0;
                }).orElse(0);
    }
}
