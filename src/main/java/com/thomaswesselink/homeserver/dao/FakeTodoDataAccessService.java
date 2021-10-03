package com.thomaswesselink.homeserver.dao;

import com.thomaswesselink.homeserver.model.Todo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository("fakeDao")
public class FakeTodoDataAccessService implements TodoDao {

    private static List<Todo> DB = new ArrayList<>();

    @Override
    public int insertTodo(UUID id, Todo todo) {
        DB.add(new Todo(id, todo.getTitle(), todo.getDescription(), todo.isDone(), todo.getDateCreated()));
        return 1;
    }

    @Override
    public List<Todo> selectAllTodos() {
        return DB;
    }
}
