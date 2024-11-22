package com.server.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;
import java.util.Optional;


@Getter
@Setter
@Document(collection = "questions")
public class Question {

    @Id
    private String id;

    private String title;

    private String body;

    @DBRef
    private User author; // Reference to the author (User object)

    @DBRef
    private List<Tag> tags; // Many-to-many relationship with tags

    // No-arg constructor
    public Question() {}
}