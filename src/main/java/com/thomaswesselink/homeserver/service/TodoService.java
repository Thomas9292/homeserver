package com.thomaswesselink.homeserver.service;

import com.thomaswesselink.homeserver.dao.TodoDao;
import com.thomaswesselink.homeserver.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TodoService {

    private final TodoDao todoDao;

    @Autowired
    public TodoService(@Qualifier("postgresDao") TodoDao todoDao) {
        this.todoDao = todoDao;
    }

    public int addTodo(Todo todo) {
        return todoDao.insertTodo(todo);
    }

    public List<Todo> getAllTodos() {
        return todoDao.selectAllTodos();
    }

    public Optional<Todo> getTodoById(UUID id) {
        return todoDao.selectTodoById(id);
    }

    public int deleteTodo(UUID id) {
        return todoDao.deleteTodoById(id);
    }

    public int updateTodo(UUID id, Todo newTodo) {
        return todoDao.updateTodoById(id, newTodo);
    }
}
