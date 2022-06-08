package com.thomaswesselink.homeserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "todo")
public class Todo {

    @Id
    @GeneratedValue
    private UUID id;

    @NotBlank
    private String title;

    private String description;

    @NotNull
    private boolean isDone;

    @CreationTimestamp
    private LocalDateTime dateCreated;
}
