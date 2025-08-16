package com.example.exam.service;

import com.example.exam.exception.NotEnoughQuestionsException;
import com.example.exam.model.Question;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {

    @Mock
    private QuestionService questionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @Test
    void getQuestions_shouldReturnCorrectAmountOfUniqueQuestions() {
        Question q1 = new Question("Q1", "A1");
        Question q2 = new Question("Q2", "A2");
        Question q3 = new Question("Q3", "A3");

        when(questionService.getAll()).thenReturn(Set.of(q1, q2, q3));
        when(questionService.getRandomQuestion()).thenReturn(q1, q2, q1, q3);

        Collection<Question> result = examinerService.getQuestions(3);

        assertEquals(3, result.size());
        assertTrue(result.containsAll(Set.of(q1, q2, q3)));
    }

    @Test
    void getQuestions_shouldThrowExceptionWhenAmountIsTooLarge() {
        when(questionService.getAll()).thenReturn(Set.of(new Question("Q1", "A1")));

        assertThrows(NotEnoughQuestionsException.class, () -> examinerService.getQuestions(2));
    }
}