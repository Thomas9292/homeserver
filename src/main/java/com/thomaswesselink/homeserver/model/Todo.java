package com.thomaswesselink.homeserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.UUID;

public class Todo {

    private final UUID id;
    private final String title;
    private final String description;
    private final boolean isDone;
    private final Date dateCreated;

    public Todo(@JsonProperty("id") UUID id,
                @JsonProperty("title") String title,
                @JsonProperty("description") String description) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isDone = false;
        this.dateCreated = new Date();
    }

    public Todo(UUID id, String title, String description, boolean isDone, Date dateCreated) {
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

    public Date getDateCreated() {
        return dateCreated;
    }

    public boolean isDone() {
        return isDone;
    }
}
