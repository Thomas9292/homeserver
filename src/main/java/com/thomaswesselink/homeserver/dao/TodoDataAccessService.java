package com.thomaswesselink.homeserver.dao;

import com.thomaswesselink.homeserver.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgresDao")
public class TodoDataAccessService implements TodoDao{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TodoDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertTodo(UUID id, Todo todo) {
        try {
            jdbcTemplate.update(
                    "INSERT INTO todo (id, title, description, isDone, dateCreated) VALUES (?, ?, ?, ?, ?)",
                    id,
                    todo.getTitle(),
                    todo.getDescription(),
                    todo.isDone(),
                    todo.getDateCreated()
            );
            return 1;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<Todo> selectAllTodos() {
        final String sql = "SELECT id, title, description, isDone, dateCreated FROM todo";
        return jdbcTemplate.query(sql, new TodoRowMapper());
    }

    @Override
    public Optional<Todo> selectTodoById(UUID id) {
        final String sql = "SELECT id, title, description, isDone, dateCreated FROM todo WHERE id = ?";
        Todo todo;
        try {
            todo = jdbcTemplate.queryForObject(sql, new TodoRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            todo = null;
        }
        return Optional.ofNullable(todo);
    }

    @Override
    public int deleteTodoById(UUID id) {
        String sql = "DELETE FROM todo WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public int updateTodoById(UUID id, Todo todo) {
        int deleted = deleteTodoById(id);
        if (deleted == 1) {
            return insertTodo(id, todo);
        }
        return 0;
    }

    @Override
    public int getNumberOfRecords() {
        String sql = "SELECT COUNT(*) FROM todo";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}
