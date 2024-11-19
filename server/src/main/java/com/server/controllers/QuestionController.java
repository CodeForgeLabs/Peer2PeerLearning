package com.server.controllers;

import com.server.models.Question;
import com.server.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    // Create a new question
    @PostMapping
    public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
        Question createdQuestion = questionService.createQuestion(question);
        return new ResponseEntity<>(createdQuestion, HttpStatus.CREATED);
    }

    // Get all questions
    @GetMapping
    public ResponseEntity<List<Question>> getAllQuestions() {
        List<Question> questions = questionService.getAllQuestions();
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    // Get a question by ID
    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable String id) {
        return questionService.getQuestionById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Get all questions by user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Question>> getQuestionsByUser(@PathVariable String userId) {
        List<Question> questions = questionService.getQuestionsByUserId(userId);
        if (questions.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    // Find questions by title
    @GetMapping("/title/{title}")
    public ResponseEntity<List<Question>> getQuestionsByTitle(@PathVariable String title) {
        List<Question> questions = questionService.getQuestionsByTitle(title);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

        // Update a question
    @PutMapping("/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable String id, @RequestBody Question question) {
        Question updatedQuestion = questionService.updateQuestion(id, question);
        return new ResponseEntity<>(updatedQuestion, HttpStatus.OK);
    }

    // Delete a question
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable String id) {
        questionService.deleteQuestion(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
