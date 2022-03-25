package ui;

import model.Question;
import model.QuestionBank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TestUserGUI implements ActionListener {
    private JFrame startTestFrame;
    private JPanel startTestPanel;
    private JLabel startTestLabel;

    private JButton startedTest;
    private JButton noQuestions;
    private JButton quizComplete;
    private JButton submitAnswer;

    private JLabel success;


    private JTextField userAnswer;

    private JFrame noQuestionsFrame;
    private JPanel noQuestionsPanel;
    private JLabel noQuestionsLabel;

    private JFrame quizCompleteFrame;
    private JPanel quizCompletePanel;
    private JLabel quizCompleteLabel;

    private JFrame askQuestionFrame;
    private JPanel askQuestionPanel;
    private JLabel askQuestionLabel;


    private QuestionBank bank = StudyBuddyApp.getBank();
    ArrayList<Question> actualQuestions = bank.getQuestions();
    ArrayList<Question> tempQuestions = (ArrayList<Question>) actualQuestions.clone();


    Question curQuestion;
    int score = 0;
    int outOf = tempQuestions.size();
    int curQuestionNumber = 1;

    private boolean correct;

    public TestUserGUI() {
        testWelcome();

    }


    private void testWelcome() {

        startTestFrame = new JFrame();
        startTestPanel = new JPanel();
        startTestLabel = new JLabel("Test Yourself!");
        startedTest = new JButton("Begin");

        startTestPanel.add(startTestLabel);
        startTestPanel.add(startedTest);
        startedTest.addActionListener(this);
        startTestPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        startTestPanel.setLayout(new GridLayout(0, 1));

        startTestFrame.add(startTestPanel, BorderLayout.CENTER);
        startTestFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startTestFrame.pack();
        startTestFrame.setVisible(true);
    }

    private void runQuiz() {
        if (outOf == 0) {
            //GUI for No Questions! return user to menu - JButton = we done
            noQuestions();
        } else if (tempQuestions.size() == 0) {
            // Window for quiz complete, show score - return user to menu
            quizComplete();
        } else {
            //Create a new window with everything for the question
            //Delete Most recent question
            // add one to score every time
            askQuestion();
        }
    }


    private void noQuestions() {

        noQuestionsFrame = new JFrame();
        noQuestionsPanel = new JPanel();
        noQuestionsLabel = new JLabel("Add some questions!");
        noQuestions = new JButton("Return to main menu");

        noQuestionsPanel.add(noQuestionsLabel);
        noQuestionsPanel.add(noQuestions);
        noQuestions.addActionListener(this);
        noQuestionsPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        noQuestionsPanel.setLayout(new GridLayout(0, 1));

        noQuestionsFrame.add(noQuestionsPanel, BorderLayout.CENTER);
        noQuestionsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        noQuestionsFrame.pack();
        noQuestionsFrame.setVisible(true);
    }

    private void quizComplete() {
        quizCompleteFrame = new JFrame();
        quizCompletePanel = new JPanel();
        quizCompleteLabel = new JLabel("Test Completed. Your score is " + score + " out of " + outOf);
        quizComplete = new JButton("Return to main menu");

        quizCompletePanel.add(quizCompleteLabel);
        quizCompletePanel.add(quizComplete);
        quizComplete.addActionListener(this);

        quizCompletePanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        quizCompletePanel.setLayout(new GridLayout(0, 1));

        quizCompleteFrame.add(quizCompletePanel, BorderLayout.CENTER);
        quizCompleteFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        quizCompleteFrame.pack();
        quizCompleteFrame.setVisible(true);
    }

    private void askQuestion() {
        curQuestion = tempQuestions.get(0);

        askQuestionFrame = new JFrame();
        askQuestionPanel = new JPanel();
        askQuestionLabel = new JLabel(curQuestionNumber + ". " + curQuestion.getQuestionPrompt());

        userAnswer = new JTextField(20);
        userAnswer.setBounds(100, 20, 165, 25);
        submitAnswer = new JButton("Submit");
        submitAnswer.setBounds(10, 80, 80, 25);
        submitAnswer.addActionListener(this);
        success = new JLabel("");
        success.setBounds(10, 110, 300, 25);


        askQuestionPanel.add(askQuestionLabel);
        askQuestionPanel.add(userAnswer);
        askQuestionPanel.add(submitAnswer);
        askQuestionPanel.add(success);
        askQuestionPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        askQuestionPanel.setLayout(new GridLayout(0, 1));

        askQuestionFrame.add(askQuestionPanel, BorderLayout.CENTER);
        askQuestionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        askQuestionFrame.pack();
        askQuestionFrame.setVisible(true);
    }

    // EFFECTS: Creates directory of methods that each buttons takes action on.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startedTest) {
            startTestFrame.dispose();
            runQuiz();
        } else if (e.getSource() == noQuestions) {
            new GUI();
            noQuestionsFrame.dispose();
        } else if (e.getSource() == quizComplete) {
            new GUI();
            quizCompleteFrame.dispose();
        } else if (e.getSource() == submitAnswer) {
            String stringUserAnswer = this.userAnswer.getText();
            String stringActualAnswer = curQuestion.getQuestionAnswer();
            tempQuestions.remove(0);
            correct = stringUserAnswer.equals(stringActualAnswer);
            curQuestionNumber++;
            if (correct) {
                score++;
            }

            askQuestionFrame.dispose();
            runQuiz();

        }

    }
}

