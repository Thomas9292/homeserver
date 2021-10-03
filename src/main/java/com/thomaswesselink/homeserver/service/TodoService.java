package com.thomaswesselink.homeserver.service;

import com.thomaswesselink.homeserver.dao.TodoDao;
import com.thomaswesselink.homeserver.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private final TodoDao todoDao;

    @Autowired
    public TodoService(@Qualifier("fakeDao") TodoDao todoDao) {
        this.todoDao = todoDao;
    }

    public int addTodo(Todo todo) {
        return todoDao.insertTodo(todo);
    }

    public List<Todo> getAllTodos() {
        return todoDao.selectAllTodos();
    }
}
