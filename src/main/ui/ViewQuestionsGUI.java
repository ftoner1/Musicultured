package ui;

import model.Question;
import model.QuestionBank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewQuestionsGUI implements ActionListener {
    private JFrame viewQuestionFrame;
    private JPanel viewQuestionPanel;
    private JLabel viewQuestionLabel;
    private JButton returnToMenu;
    private QuestionBank bank = StudyBuddyApp.getBank();


    public ViewQuestionsGUI() {
        viewQuestionFrame = new JFrame();
        viewQuestionPanel = new JPanel();
        viewQuestionLabel = new JLabel("^^^Current Questions^^^ ");
        returnToMenu = new JButton("Return to Menu");
        int curQuestion = 1;

        for (Question q: bank.getQuestions()) {
            String questionString = (curQuestion + ". " + "q: " + q.getQuestionPrompt() + "  a: " + q.getQuestionAnswer());
            JLabel curLabel = new JLabel(questionString);
            viewQuestionPanel.add(curLabel);
            curQuestion++;
        }

        viewQuestionPanel.add(viewQuestionLabel);
        returnToMenu.addActionListener(e -> {
            new GUI();
            viewQuestionFrame.dispose();
        });
        viewQuestionPanel.add(returnToMenu);
        viewQuestionPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        viewQuestionPanel.setLayout(new GridLayout(0, 1));

        viewQuestionFrame.add(viewQuestionPanel, BorderLayout.CENTER);
        viewQuestionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        viewQuestionFrame.pack();
        viewQuestionFrame.setVisible(true);
    }


    // EFFECTS: Crates directory of methods that each buttons takes action on.

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == returnToMenu) {
            viewQuestionFrame.dispose();
            new GUI();
        }
    }
}
