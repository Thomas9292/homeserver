package com.thomaswesselink.homeserver;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thomaswesselink.homeserver.api.TodoController;
import com.thomaswesselink.homeserver.dao.TodoDao;
import com.thomaswesselink.homeserver.model.Todo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@WebMvcTest(TodoController.class)
public class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    TodoDao todoDao;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testAddTodo() throws Exception {
        Todo testTodo = createTodo();

        when(todoDao.save(testTodo)).thenReturn(testTodo);
        mockMvc.perform(
                post("/api/v1/todo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(testTodo)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(testTodo.getTitle()));
    }

    @Test
    public void testGetAllTodos() throws Exception {
        List<Todo> testTodos = Arrays.asList(createTodo(), createTodo());

        when(todoDao.findAll()).thenReturn(testTodos);
        MvcResult result = mockMvc.perform(
                        get("/api/v1/todo"))
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        List<Todo> actual = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<Todo>>() {});
        Assertions.assertEquals(actual, testTodos);
    }

    @Test
    public void testGetTodoById() throws Exception {
        UUID testId = UUID.randomUUID();
        Todo testTodo = createTodo(testId);

        when(todoDao.findById(testId)).thenReturn(Optional.of(testTodo));
        mockMvc.perform(
                        get("/api/v1/todo/" + testId))
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(testTodo.getId().toString()));
    }

    @Test
    public void testDeleteTodoById() throws Exception {
        UUID testId = UUID.randomUUID();

        mockMvc.perform(
                        delete("/api/v1/todo/" + testId))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testUpdateTodo() throws Exception {
        UUID testId = UUID.randomUUID();
        Todo testTodo = createTodo(testId);

        when(todoDao.save(testTodo)).thenReturn(testTodo);
        mockMvc.perform(
                        put("/api/v1/todo/" + testId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(testTodo)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(testTodo.getTitle()));
    }

    // TODO: Add tests for unsuccessful cases

    private Todo createTodo() {
        return new Todo(
                null,
                "Todo",
                "Description",
                false,
                null
        );
    }

    private Todo createTodo(UUID id) {
        Todo todo = createTodo();
        todo.setId(id);
        return todo;
    }

    private String asJsonString(final Object obj) {
        try {
            return this.mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
