package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class QuestionBank implements Writable {
    private String bankName;
    private ArrayList<Question> questions;


    public QuestionBank(String userBankName) {
        this.bankName = userBankName;
        this.questions = new ArrayList<>();

    }

    //
    public void setBankName(String name) {
        this.bankName = name;
    }

    public String getBankName() {
        return this.bankName;
    }

    // EFFECTS: returns the number of Questions in the QuestionBank

    public int numQuestions() {
        return questions.size();
    }

    // MODIFIES: this
    // EFFECTS: adds newQuestion to the back of questions

    public void addQuestion(Question newQuestion) {
        questions.add(newQuestion);
    }

    // EFFECTS: returns true if bank is empty, false otherwise

    public boolean isEmpty() {
        return (this.questions.isEmpty());
    }

    // REQUIRES: there is a Question in the QuestionBank
    // MODIFIES: this
    // EFFECTS: removes the most recently added question from the bank

    public void removeQuestion() {
        if (questions.size() >= 1) {
            questions.remove((questions.size()) - 1);
        } else {
            return;
        }
    }

    // REQUIRES: at least 1 question in the bank
    // EFFECTS: prints out all of the questions in the bank, with question and answer prefaced by 'q:' and 'a:'


    public ArrayList<Question> getQuestions() {
        return this.questions;
    }

    // REQUIRES: there is at least 1 question in the bank
    // EFFECTS: returns the question most recently added to the bank

    public Question getRecentQuestion() {
        if (questions.size() == 0) {
            return null;
        } else {
            return questions.get((questions.size()) - 1);
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("bank name", bankName);
        json.put("questions", questionsToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray questionsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Question q : questions) {
            jsonArray.put(q.toJson());
        }

        return jsonArray;
    }
}
