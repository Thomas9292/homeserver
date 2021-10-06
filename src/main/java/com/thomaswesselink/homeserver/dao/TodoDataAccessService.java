package com.thomaswesselink.homeserver.dao;

import com.thomaswesselink.homeserver.model.Todo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgresDao")
public class TodoDataAccessService implements TodoDao{
    @Override
    public int insertTodo(UUID id, Todo todo) {
        return 0;
    }

    @Override
    public List<Todo> selectAllTodos() {
        return null;
    }

    @Override
    public Optional<Todo> selectTodoById(UUID id) {
        return Optional.empty();
    }

    @Override
    public int deleteTodoById(UUID id) {
        return 0;
    }

    @Override
    public int updateTodoById(UUID id, Todo todo) {
        return 0;
    }

    @Override
    public int getNumberOfRecords() {
        return 0;
    }
}
