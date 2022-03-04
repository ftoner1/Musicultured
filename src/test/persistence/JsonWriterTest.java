package persistence;

import model.QuestionBank;
import model.Question;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            QuestionBank wr = new QuestionBank("My question bank");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyQuestionBank() {
        try {
            QuestionBank qb = new QuestionBank("My question bank");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyQBank.json");
            writer.open();
            writer.write(qb);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyQBank.json");
            qb = reader.read();
            assertEquals("My question bank", qb.getBankName());
            assertEquals(0, qb.numQuestions());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralQuestionBank() {
        try {
            QuestionBank qb = new QuestionBank("My question bank");
            qb.addQuestion(new Question("yes?", "yes"));
            qb.addQuestion(new Question("no?", "no"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralQBank.json");
            writer.open();
            writer.write(qb);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralQBank.json");
            qb = reader.read();
            assertEquals("My question bank", qb.getBankName());
            List<Question> questions = qb.getQuestions();
            assertEquals(2, questions.size());
            checkQuestion("yes?", "yes", questions.get(0));
            checkQuestion("no?", "no", questions.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}