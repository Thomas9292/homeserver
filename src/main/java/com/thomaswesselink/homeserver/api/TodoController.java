package com.thomaswesselink.homeserver.api;

import com.thomaswesselink.homeserver.dao.TodoDao;
import com.thomaswesselink.homeserver.model.Todo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/todo")
@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoDao todoDao;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Todo addTodo(@RequestBody @NonNull @Valid Todo todo) {
        return todoDao.save(todo);
    }

    @GetMapping
    public List<Todo> getAllTodos() {
        return todoDao.findAll();
    }

    @GetMapping(path = "{id}")
    public Todo getTodoById(@PathVariable("id") UUID id) {
        return todoDao.findById(id).orElse(null);
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTodoById(@PathVariable("id") UUID id) {
        todoDao.deleteById(id);
    }

    @PutMapping(path = "{id}")
    public Todo updateTodoById(@PathVariable("id") UUID id,
                               @Valid @NonNull @RequestBody Todo todo) {
        return todoDao.save(todo);
    }
}
