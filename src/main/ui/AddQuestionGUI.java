package ui;

import model.Question;
import model.QuestionBank;
import ui.StudyBuddyApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;

// GUI for user adding a new question
public class AddQuestionGUI implements ActionListener {

    private JFrame addQuestionFrame;
    private JPanel addQuestionPanel;
    private JLabel addQuestionLabel;
    private JTextField questionPrompt;

    private JButton submittedPrompt;
    private JButton submittedAnswer;
    private JButton showedMessage;
    private JLabel success;

    private JFrame answerFrame;
    private JPanel answerPanel;
    private JLabel answerLabel;
    private JTextField userAnswer;

    private JFrame messageFrame;
    private JPanel messagePanel;
    private JLabel messageLabel;

    private Question newQuestion;
    private QuestionBank bank = StudyBuddyApp.getBank();


    //EFFECT: Opens GUI for user asking a question

    public AddQuestionGUI() {
        bank = StudyBuddyApp.getBank();
        addQuestionFrame = new JFrame();
        addQuestionPanel = new JPanel();
        addQuestionLabel = new JLabel("Enter Question:");
        questionPrompt = new JTextField(20);
        questionPrompt.setBounds(100, 20, 165, 25);
        submittedPrompt = new JButton("Submit");
        submittedPrompt.setBounds(10, 80, 80, 25);
        submittedPrompt.addActionListener(this);
        success = new JLabel("");
        success.setBounds(10, 110, 300, 25);

        addQuestionPanel.add(addQuestionLabel);
        addQuestionPanel.add(questionPrompt);
        addQuestionPanel.add(submittedPrompt);
        addQuestionPanel.add(success);
        addQuestionPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        addQuestionPanel.setLayout(new GridLayout(0, 1));

        addQuestionFrame.add(addQuestionPanel, BorderLayout.CENTER);
        addQuestionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addQuestionFrame.pack();
        addQuestionFrame.setVisible(true);


    }


    // EFFECTS: Opens the answer submission page
    public void openSubmitAnswer() {

        answerFrame = new JFrame();
        answerPanel = new JPanel();
        answerLabel = new JLabel("Enter Answer: ");
        userAnswer = new JTextField(20);
        userAnswer.setBounds(100, 20, 165, 25);
        submittedAnswer = new JButton("Submit");
        submittedAnswer.setBounds(10, 80, 80, 25);
        submittedAnswer.addActionListener(this);

        answerPanel.add(answerLabel);
        answerPanel.add(userAnswer);
        answerPanel.add(submittedAnswer);

        answerPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        answerPanel.setLayout(new GridLayout(0, 1));

        answerFrame.add(answerPanel, BorderLayout.CENTER);
        answerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        answerFrame.pack();
        answerFrame.setVisible(true);

    }

    // EFFECTS: prints a message showing that question has been successfully added
    public void addedQuestionMessage() {
        messageFrame = new JFrame();
        messagePanel = new JPanel();
        messageLabel = new JLabel("Question Added!");
        showedMessage = new JButton("Return to Menu");
        showedMessage.addActionListener(e -> {
            new GUI();
            messageFrame.dispose();
        });

        messagePanel.add(messageLabel);
        messagePanel.add(showedMessage);

        messagePanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        messagePanel.setLayout(new GridLayout(0, 1));

        messageFrame.add(messagePanel, BorderLayout.CENTER);
        messageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        messageFrame.pack();
        messageFrame.setVisible(true);

    }


    // EFFECTS: Creates a directory for the return button in locationFrame.
    private void returnToMenu() {
        new GUI();
    }


    // EFFECTS: Creates directory of methods that each buttons takes action on.

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submittedPrompt) {
            openSubmitAnswer();
            addQuestionFrame.dispose();
        } else if (e.getSource() == submittedAnswer) {
            String questionPromptText = this.questionPrompt.getText();
            String userAnswerText = this.userAnswer.getText();
            newQuestion = new Question(questionPromptText, userAnswerText);
            bank.addQuestion(newQuestion);
            addedQuestionMessage();
            answerFrame.dispose();

        } else if (e.getSource() == showedMessage) {
            messageFrame.dispose();
            returnToMenu();
        }
    }
}
