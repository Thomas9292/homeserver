package com.thomaswesselink.homeserver.api;

import com.thomaswesselink.homeserver.model.Todo;
import com.thomaswesselink.homeserver.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @GetMapping(path = "{id}")
    public Todo getTodoById(@PathVariable("id") UUID id) {
        return todoService.getTodoById(id).orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deleteTodoById(@PathVariable("id") UUID id) {
        todoService.deleteTodo(id);
    }

    @PutMapping(path = "{id}")
    public void updateTodoById(@PathVariable("id") UUID id, @RequestBody Todo todo) {
        todoService.updateTodo(id, todo);
    }
}
