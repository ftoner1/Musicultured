package ui;

import model.QuestionBank;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

// GUI for user saving current questions
public class SaveQuestionsGUI {

    private JFrame savedQuestionsFrame;
    private JPanel savedQuestionsPanel;
    private JLabel savedQuestionsLabel;
    private JButton savedQuestions;
    private JButton enterButton;

    private JsonWriter jsonWriter;
    private static final String JSON_STORE = "./data/qbank.json";

    private JPanel errorPanel;
    private JLabel errorMessage;
    private JButton errorButton;
    private QuestionBank bank = StudyBuddyApp.getBank();


    // EFFECTS: Creates new window showing user saved questions
    public SaveQuestionsGUI() {
        jsonWriter = new JsonWriter(JSON_STORE);
        saveQuestionBank();

        savedQuestionsFrame = new JFrame();
        savedQuestionsPanel = new JPanel();
        savedQuestionsLabel = new JLabel("Saved Questions!");
        savedQuestions = new JButton("Return to Menu");
        savedQuestions.addActionListener(e -> {
            new GUI();
            savedQuestionsFrame.dispose();
        });
        savedQuestionsPanel.add(savedQuestionsLabel);
        savedQuestionsPanel.add(savedQuestions);
        savedQuestionsPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        savedQuestionsPanel.setLayout(new GridLayout(0, 1));

        savedQuestionsFrame.add(savedQuestionsPanel, BorderLayout.CENTER);
        savedQuestionsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        savedQuestionsFrame.pack();
        savedQuestionsFrame.setVisible(true);
    }


    // EFFECTS: Saves the current QuestionBank
    public void saveQuestionBank() {
        try {
            jsonWriter.open();
            jsonWriter.write(bank);
            jsonWriter.close();

        } catch (FileNotFoundException e) {
            errorPanel = new JPanel();
            errorMessage = new JLabel("Error: Unable to save questions");
            errorButton = new JButton("Return to Menu");
            enterButton.addActionListener(e1 -> {
                new GUI();
                savedQuestionsFrame.dispose();
            });

            errorPanel.add(errorMessage);
            errorPanel.add(errorButton);

        }
    }
}
