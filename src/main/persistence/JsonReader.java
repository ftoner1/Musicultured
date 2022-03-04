package persistence;

import model.Question;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.QuestionBank;
import org.json.*;

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads QuestionBank from file and returns it;
    // throws IOException if an error occurs reading data from file
    public QuestionBank read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseQuestionBank(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }


    // EFFECTS: parses QuestionBank from JSON object and returns it
    private QuestionBank parseQuestionBank(JSONObject jsonObject) {
        String name = jsonObject.getString("bank name");
        QuestionBank wr = new QuestionBank(name);
        addQuestions(wr, jsonObject);
        return wr;
    }

    // MODIFIES: wr
    // EFFECTS: parses questions from JSON object and adds them to QuestionBank
    private void addQuestions(QuestionBank wr, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("questions");
        for (Object json : jsonArray) {
            JSONObject nextQuestion = (JSONObject) json;
            addQuestion(wr, nextQuestion);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses Question from JSON object and adds it to QuestionBank
    private void addQuestion(QuestionBank wr, JSONObject jsonObject) {
        String question = jsonObject.getString("question");
        String answer = jsonObject.getString("answer");
        Question addedQuestion = new Question(question, answer);
        wr.addQuestion(addedQuestion);
    }
}
