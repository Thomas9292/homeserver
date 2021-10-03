package com.thomaswesselink.homeserver.api;

import com.thomaswesselink.homeserver.model.Todo;
import com.thomaswesselink.homeserver.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/todo")
@RestController
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public void addTodo(@RequestBody Todo todo) {
        todoService.addTodo(todo);
    }

    @GetMapping
    public List<Todo> getAllPeople() {
        return todoService.getAllTodos();
    }
}
