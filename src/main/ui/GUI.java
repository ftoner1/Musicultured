package ui;

import model.QuestionBank;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {

    private JFrame frame;
    private JPanel panel;
    private JLabel label;
    private JButton addQuestion;
    private JButton deleteQuestion;
    private JButton testYourself;
    private JButton loadQuestions;
    private JButton saveQuestions;
    private JButton viewQuestions;
    private ImageIcon logo;

    private QuestionBank bank = StudyBuddyApp.getBank();
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    private static final String JSON_STORE = "./data/qbank.json";


    // EFFECTS: Opens GUI for StudyBuddy
    public GUI() {
        frame = new JFrame();
        initializeWelcomePage();
        addQuestion.addActionListener(this);
        deleteQuestion.addActionListener(this);
        testYourself.addActionListener(this);
        loadQuestions.addActionListener(this);
        saveQuestions.addActionListener(this);
        viewQuestions.addActionListener(this);

        panel = new JPanel();
        panel.setBorder(BorderFactory.createMatteBorder(30, 30, 10, 30, Color.white));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(label);
        panel.setBackground(Color.MAGENTA);
        addToPanel();

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("StudyBuddy");
        frame.pack();
        frame.setVisible(true);

    }
    // EFFECTS: Initializes the home menu

    public void initializeWelcomePage() {
        label = new JLabel("StudyBuddy ver. 1.3");
        logo = new ImageIcon("./data/galaxy-.jpg");
        label.setIcon(logo);
        label.setFont(new Font("Futura", Font.BOLD, 35));
        label.setForeground(Color.BLACK);
        label.setIconTextGap(10);
        addQuestion = new JButton("Add a new question");
        deleteQuestion = new JButton("Delete most recent question");
        testYourself = new JButton("Test yourself!");
        loadQuestions = new JButton("Load questions from file");
        saveQuestions = new JButton("Save current questions to file");
        viewQuestions = new JButton("View all current questions");
    }

    // EFFECTS: Adds labelled buttons to welcome panel
    public void addToPanel() {
        panel.add(addQuestion);
        panel.add(deleteQuestion);
        panel.add(testYourself);
        panel.add(loadQuestions);
        panel.add(saveQuestions);
        panel.add(viewQuestions);
    }

    // EFFECTS: Crates directory of methods that each buttons takes action on.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addQuestion) {
            new AddQuestionGUI();
            frame.dispose();
        } else if (e.getSource() == deleteQuestion) {
            new ViewQuestionsGUI();
            frame.dispose();
        } else if (e.getSource() == testYourself) {
            new TestUserGUI();
            frame.dispose();
        } else if (e.getSource() == loadQuestions) {
            new LoadQuestionsGUI();
            frame.dispose();
        } else if (e.getSource() == saveQuestions) {
            new SaveQuestionsGUI();
            frame.dispose();
        } else if (e.getSource() == viewQuestions) {
            new ViewQuestionsGUI();
            frame.dispose();
        }
    }



}
