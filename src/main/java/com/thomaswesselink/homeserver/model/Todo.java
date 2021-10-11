package com.thomaswesselink.homeserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

public class Todo {

    private final UUID id;
    @NotBlank
    private final String title;
    private final String description;
    @NotNull
    private final boolean isDone;
    private final LocalDateTime dateCreated;

    public Todo(@JsonProperty("id") UUID id,
                @JsonProperty("title") String title,
                @JsonProperty("description") String description) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isDone = false;
        this.dateCreated = LocalDateTime.now();
    }

    public Todo(UUID id, String title, String description, boolean isDone, LocalDateTime dateCreated) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isDone = isDone;
        this.dateCreated = dateCreated;
    }


    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public boolean isDone() {
        return isDone;
    }
}
