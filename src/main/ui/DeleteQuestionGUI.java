package ui;

import model.Question;
import model.QuestionBank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteQuestionGUI implements ActionListener {
    private JFrame deleteQuestionFrame;
    private JPanel deleteQuestionPanel;
    private JLabel deleteQuestionLabel;
    private JButton deletedMessage;
    private QuestionBank bank = StudyBuddyApp.getBank();

    public DeleteQuestionGUI() {
        deleteQuestionFrame = new JFrame();
        deleteQuestionPanel = new JPanel();
        deleteQuestionLabel = new JLabel("Deleted most recent Question!");
        deletedMessage = new JButton("Return to Menu");

        deletedMessage.addActionListener(e -> {
            bank.removeQuestion();
            new GUI();
            deleteQuestionFrame.dispose();
        });
        deleteQuestionPanel.add(deleteQuestionLabel);
        deleteQuestionPanel.add(deletedMessage);
        deleteQuestionPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        deleteQuestionPanel.setLayout(new GridLayout(0, 1));

        deleteQuestionFrame.add(deleteQuestionPanel, BorderLayout.CENTER);
        deleteQuestionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        deleteQuestionFrame.pack();
        deleteQuestionFrame.setVisible(true);
    }


    // EFFECTS: Crates directory of methods that each buttons takes action on.

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == deletedMessage) {
            deleteQuestionFrame.dispose();
            new GUI();
        }
    }
}
