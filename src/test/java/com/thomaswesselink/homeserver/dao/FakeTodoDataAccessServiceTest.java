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
        assertEquals(2, testService.selectAllTodos().size());
    }

    @Test
    void selectTodoById() {
        testService.insertTodo(testTodo.getId(), testTodo);
        assertFalse(testService.selectTodoById(UUID.randomUUID()).isPresent());
        assertEquals(testService.selectTodoById(testTodo.getId()).get().getId(), testTodo.getId());
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