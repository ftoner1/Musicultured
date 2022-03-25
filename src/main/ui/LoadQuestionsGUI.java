package ui;

import model.Question;
import model.QuestionBank;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LoadQuestionsGUI {
    private JFrame loadQuestionsFrame;
    private JPanel loadQuestionsPanel;
    private JLabel loadQuestionsLabel;
    private JButton loadedQuestions;
    private JButton enterButton;


    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/qbank.json";

    private JPanel errorPanel;
    private JLabel errorMessage;
    private JButton errorButton;
    private QuestionBank bank = StudyBuddyApp.getBank();

    public LoadQuestionsGUI() {
        jsonReader = new JsonReader(JSON_STORE);
        loadQuestionBank();


        loadQuestionsFrame = new JFrame();
        loadQuestionsPanel = new JPanel();
        loadQuestionsLabel = new JLabel("Loaded Questions" + " from " + JSON_STORE);
        loadedQuestions = new JButton("Return to Menu");
        loadedQuestions.addActionListener(e -> {
            new GUI();
            loadQuestionsFrame.dispose();
        });
        loadQuestionsPanel.add(loadQuestionsLabel);
        loadQuestionsPanel.add(loadedQuestions);
        loadQuestionsPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        loadQuestionsPanel.setLayout(new GridLayout(0, 1));

        loadQuestionsFrame.add(loadQuestionsPanel, BorderLayout.CENTER);
        loadQuestionsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loadQuestionsFrame.pack();
        loadQuestionsFrame.setVisible(true);
    }


    // EFFECTS: Loads the saved QuestionBank
    public void loadQuestionBank() {
        try {
            bank = jsonReader.read();

        } catch (FileNotFoundException e) {
            errorPanel = new JPanel();
            errorMessage = new JLabel("Error: Unable to read from file");
            errorButton = new JButton("Return to Menu");
            enterButton.addActionListener(e1 -> {
                new GUI();
                loadQuestionsFrame.dispose();
            });

            errorPanel.add(errorMessage);
            errorPanel.add(errorButton);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
