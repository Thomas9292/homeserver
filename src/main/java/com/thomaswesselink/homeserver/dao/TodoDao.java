package com.thomaswesselink.homeserver.dao;

import com.thomaswesselink.homeserver.model.Todo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TodoDao extends CrudRepository<Todo, UUID> {

    Todo save(Todo todo);

    List<Todo> findAll();

    Optional<Todo> findById(UUID id);

    void deleteById(UUID uuid);

    long count();
}
