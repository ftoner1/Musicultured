package ui;

import model.Question;

import javax.swing.*;

public class ViewQuestionsGUI {

    JFrame frame = new JFrame("test");
    private JList<Question> questions;
    DefaultListModel<Question> model;
    JLabel label;
    JPanel panel = new JPanel();
    JSplitPane splitPane = new JSplitPane();

    public ViewQuestionsGUI() {
        questions.setModel(model);
        model.addElement(new Question ("d", "a"));

        splitPane.setLeftComponent(new JScrollPane(questions));
        panel.add(label);
        splitPane.setRightComponent(panel);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
