package model;

import java.util.ArrayList;
import java.util.List;

public class QuestionBank {
    private String bankName;
    private ArrayList<Question> questions;

    public QuestionBank() {
        this.bankName = "";
        this.questions = new ArrayList<>();
    }

    public QuestionBank(String userBankName) {
        this.bankName = userBankName;
        this.questions = new ArrayList<>();

    }


    public void setBankName(String name) {
        this.bankName = name;
    }

    public String getBankName() {
        return this.bankName;
    }

    public int numQuestions() {
        return questions.size();
    }

    public void addQuestion(Question newQuestion) {
        questions.add(newQuestion);
    }

    public void removeQuestion() {
        if (questions.size() >= 1) {
            questions.remove((questions.size()) - 1);
        } else {
            return;
        }
    }


    public Question getRecentQuestion() {
        if (questions.size() == 0) {
            return null;
        } else {
            return questions.get((questions.size()) - 1);
        }
    }
}



