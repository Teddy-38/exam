package com.example.exam.service;

import com.example.exam.model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class JavaQuestionServiceTest {

    private JavaQuestionService questionService;

    @BeforeEach
    void setUp() {
        questionService = new JavaQuestionService();
    }

    @Test
    void add_shouldAddQuestionAndReturnIt() {
        Question expected = new Question("Q1", "A1");
        Question actual = questionService.add("Q1", "A1");
        assertEquals(expected, actual);
        assertTrue(questionService.getAll().contains(expected));
    }

    @Test
    void remove_shouldRemoveQuestionAndReturnIt() {
        Question question = new Question("Q1", "A1");
        questionService.add(question);

        Question removed = questionService.remove(question);
        assertEquals(question, removed);
        assertFalse(questionService.getAll().contains(question));
    }

    @Test
    void getAll_shouldReturnAllAddedQuestions() {
        Question q1 = questionService.add("Q1", "A1");
        Question q2 = questionService.add("Q2", "A2");

        Collection<Question> all = questionService.getAll();
        assertEquals(2, all.size());
        assertTrue(all.contains(q1));
        assertTrue(all.contains(q2));
    }

    @Test
    void getRandomQuestion_shouldThrowExceptionWhenEmpty() {
        assertThrows(NoSuchElementException.class, () -> questionService.getRandomQuestion());
    }

    @Test
    void getRandomQuestion_shouldReturnAQuestionFromSet() {
        questionService.add("Q1", "A1");
        Question randomQuestion = questionService.getRandomQuestion();
        assertNotNull(randomQuestion);
        assertTrue(questionService.getAll().contains(randomQuestion));
    }
}