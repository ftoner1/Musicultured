package model;

public class Question {

    private String question;
    private String answer;

    public Question(String userQuestion, String userAnswer) {
        this.question = userQuestion;
        this.answer = userAnswer;
    }

    //
    public String getQuestionPrompt() {
        return this.question;
    }

    public void setQuestionPrompt(String newQuestion) {
        this.question = newQuestion;
    }

    public void setQuestionAnswer(String newAnswer) {
        this.answer = newAnswer;
    }


    public String getQuestionAnswer() {
        return this.answer;
    }


    public boolean isCorrect(String inputAnswer) {
        return inputAnswer.equalsIgnoreCase(this.answer);
    }





}
