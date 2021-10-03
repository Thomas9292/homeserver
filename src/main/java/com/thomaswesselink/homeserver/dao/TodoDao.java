package com.thomaswesselink.homeserver.dao;

import com.thomaswesselink.homeserver.model.Todo;

import java.util.List;
import java.util.UUID;

public interface TodoDao {

    int insertTodo(UUID id, Todo todo);

    default int insertTodo(Todo todo) {
        UUID id = UUID.randomUUID();
        return insertTodo(id, todo);
    }

    List<Todo> selectAllTodos();
}
