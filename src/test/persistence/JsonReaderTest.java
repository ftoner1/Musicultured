package persistence;

import model.Question;
import model.QuestionBank;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            QuestionBank qb = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyQuestionBank() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyQBank.json");
        try {
            QuestionBank qb = reader.read();
            assertEquals("empty bank", qb.getBankName());
            assertEquals(0, qb.numQuestions());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralQuestionBank() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralQBank.json");
        try {
            QuestionBank qb = reader.read();
            assertEquals("My question bank", qb.getBankName());
            List<Question> questions = qb.getQuestions();
            assertEquals(2, questions.size());
            checkQuestion("yes?", "yes", questions.get(0));
            checkQuestion("no?", "no", questions.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}