package model;

import org.json.JSONObject;
import persistence.Writable;

public class Question implements Writable {

    private String question;
    private String answer;


    // REQUIRES: userQuestion and userAnswer have non-zero lengths
    // Question question prompt is set to userQuestion, answer is set to userAnswer.

    public Question(String userQuestion, String userAnswer) {
        this.question = userQuestion;
        this.answer = userAnswer;
    }

    // EFFECTS: returns question prompt of a user-created Question

    public String getQuestionPrompt() {
        return this.question;
    }
    // MODIFIES: this
    // EFFECTS: changes the question prompt of a Question

    public void setQuestionPrompt(String newQuestion) {
        this.question = newQuestion;
    }

    // MODIFIES: this
    // EFFECTS: changes the answer of a Question

    public void setQuestionAnswer(String newAnswer) {
        this.answer = newAnswer;
    }

    // EFFECTS: returns the answer of a Question

    public String getQuestionAnswer() {
        return this.answer;
    }
    // EFFECTS: prints out a Question's question


    // REQUIRES: inputAnswer is not empty
    // EFFECTS: returns true if input is equal to answer
    // otherwise, return false

    public boolean isCorrect(String inputAnswer) {
        return inputAnswer.equalsIgnoreCase(this.answer);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("question", question);
        json.put("answer", answer);
        return json;
    }


}
