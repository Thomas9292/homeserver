package com.thomaswesselink.homeserver.dao;

import com.thomaswesselink.homeserver.model.Todo;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class FakeTodoDataAccessServiceTest {

    private FakeTodoDataAccessService testService = new FakeTodoDataAccessService();
    private final Todo testTodo = new Todo(UUID.randomUUID(),
            "Test",
            "Test description.");

    @Test
    void insertTodo() {
        int numBefore = testService.getNumberOfRecords();
        testService.insertTodo(testTodo);
        assertEquals(numBefore + 1, testService.getNumberOfRecords());
    }

    @Test
    void selectAllTodos() {
        testService.insertTodo(testTodo);
        testService.insertTodo(testTodo);
        assertEquals(testService.getNumberOfRecords(), testService.selectAllTodos().size());
    }

    @Test
    void selectTodoById() {
        testService.insertTodo(testTodo.getId(), testTodo);
        assertFalse(testService.selectTodoById(UUID.randomUUID()).isPresent());
        Todo retrievedTodo = testService.selectTodoById(testTodo.getId()).get();
        assertEquals(retrievedTodo.getId(), testTodo.getId());
        assertEquals(retrievedTodo.getDescription(), testTodo.getDescription());
        assertEquals(retrievedTodo.getTitle(), testTodo.getTitle());
        assertEquals(retrievedTodo.isDone(), testTodo.isDone());
        assertEquals(retrievedTodo.getDateCreated(), testTodo.getDateCreated());
    }

    @Test
    void deleteTodoById() {
        int numBefore = testService.getNumberOfRecords();
        UUID id = UUID.randomUUID();
        testService.insertTodo(id, testTodo);
        testService.deleteTodoById(id);
        assertEquals(numBefore, testService.getNumberOfRecords());
    }

    @Test
    void updateTodoById() {
        UUID id = UUID.randomUUID();
        String newDescription = "Changed.";
        testService.insertTodo(id, testTodo);
        testService.updateTodoById(id, new Todo(id, "Title", newDescription));
        assertEquals(testService.selectTodoById(id).get().getDescription(), newDescription);
    }
}