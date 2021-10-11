package com.thomaswesselink.homeserver.dao;

import com.thomaswesselink.homeserver.model.Todo;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@AutoConfigureEmbeddedDatabase
public class TodoDataAccessServiceTest {

    @Autowired
    private TodoDataAccessService todoDataAccessService;

    private final Todo testTodo = new Todo(UUID.randomUUID(),
            "Test",
            "Test description.");

    @Test
    void insertTodo() {
        int numBefore = todoDataAccessService.getNumberOfRecords();
        todoDataAccessService.insertTodo(testTodo);
        assertEquals(numBefore + 1, todoDataAccessService.getNumberOfRecords());
    }

    @Test
    void selectAllTodos() {
        todoDataAccessService.insertTodo(testTodo);
        todoDataAccessService.insertTodo(testTodo);
        assertEquals(todoDataAccessService.getNumberOfRecords(), todoDataAccessService.selectAllTodos().size());
    }

    @Test
    void selectTodoById() {
        todoDataAccessService.insertTodo(testTodo.getId(), testTodo);
        assertFalse(todoDataAccessService.selectTodoById(UUID.randomUUID()).isPresent());
        Todo retrievedTodo = todoDataAccessService.selectTodoById(testTodo.getId()).get();
        assertEquals(retrievedTodo.getId(), testTodo.getId());
        assertEquals(retrievedTodo.getDescription(), testTodo.getDescription());
        assertEquals(retrievedTodo.getTitle(), testTodo.getTitle());
        assertEquals(retrievedTodo.isDone(), testTodo.isDone());
        assertEquals(retrievedTodo.getDateCreated(), testTodo.getDateCreated());
    }

    @Test
    void deleteTodoById() {
        int numBefore = todoDataAccessService.getNumberOfRecords();
        UUID id = UUID.randomUUID();
        todoDataAccessService.insertTodo(id, testTodo);
        todoDataAccessService.deleteTodoById(id);
        assertEquals(numBefore, todoDataAccessService.getNumberOfRecords());
    }

    @Test
    void updateTodoById() {
        UUID id = UUID.randomUUID();
        String newDescription = "Changed.";
        todoDataAccessService.insertTodo(id, testTodo);
        todoDataAccessService.updateTodoById(id, new Todo(id, "Title", newDescription));
        assertEquals(todoDataAccessService.selectTodoById(id).get().getDescription(), newDescription);
    }
}