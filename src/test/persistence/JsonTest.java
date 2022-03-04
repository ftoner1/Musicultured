package persistence;

import model.Question;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
        protected void checkQuestion(String questionPrompt, String questionAnswer, Question question) {
        assertEquals(questionPrompt, question.getQuestionPrompt());
        assertEquals(questionAnswer, question.getQuestionAnswer());
    }
}
