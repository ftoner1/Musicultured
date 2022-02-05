package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestionTest {
    private Question testQuestion;

    @BeforeEach
    void runBefore() {
        testQuestion = new Question("Question?", "Answer.");
    }


    @Test
    void testConstructor() {
        assertEquals(testQuestion.getQuestionPrompt(), "Question?");
        assertEquals(testQuestion.getQuestionAnswer(), "Answer.");
    }


    @Test
    void testIsCorrect() {
        assertEquals(testQuestion.isCorrect("Answer."), true);
        assertEquals(testQuestion.isCorrect("Wrong"), false);
    }

    @Test

    void testSetters() {
        testQuestion.setQuestionPrompt("newQuestion");
        testQuestion.setQuestionAnswer("newAnswer");
        assertEquals(testQuestion.getQuestionPrompt(), "newQuestion");
        assertEquals(testQuestion.getQuestionAnswer(), "newAnswer");
    }

}