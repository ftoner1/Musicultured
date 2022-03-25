package ui;

import model.Question;
import model.QuestionBank;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class StudyBuddyApp {
    private static QuestionBank bank;
    private Scanner input;
    private static JsonReader jsonReader;
    private JsonWriter jsonWriter;
    private static final String JSON_STORE = "./data/qbank.json";


    // EFFECTS: Runs the StudyBuddy application

    public StudyBuddyApp() {
        runStudyBuddy();
    }

    private void runStudyBuddy() {
        boolean keepGoing = true;
        String command = null;

        init();
        System.out.println("(/^ ▽ ^)/ Welcome to the StudyBuddy! :");

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase(Locale.ROOT);
            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }

        }

        System.out.println("\n(✪‿✪)ノ Let's study again soon!");
    }

    private void processCommand(String command) {
        if (command.equals("a")) {
            addQuestion();
        } else if (command.equals("d")) {
            removeQuestion();
        } else if (command.equals("v")) {
            viewQuestions();
        } else if (command.equals("t")) {
            testUser();
        } else if (command.equals("s")) {
            saveQuestionBank();
        } else if (command.equals("l")) {
            try {
                loadQuestionBank();
            } catch (IOException i) {
            }
        } else {
            System.out.println("(╯°□°）╯︵ ┻━┻ Please enter a valid selection!");
        }
    }

    // EFFECTS: displays menu of options to user

    private void displayMenu() {
        System.out.println("\ta -> add a question");
        System.out.println("\td -> delete most recent question");
        System.out.println("\tt -> test yourself!");
        System.out.println("\tv -> view questions");
        System.out.println("\tl -> load questions from file");
        System.out.println("\ts -> save questions to file");
        System.out.println("\tq -> quit");
    }


    private void init() {
        bank = new QuestionBank("userBank");
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
    }


    private void addQuestion() {
        System.out.println("Enter your question: \n");
        String question = input.next();
        System.out.println("\n Enter your answer: \n");
        String answer = input.next();
        Question questionObject = new Question(question, answer);
        bank.addQuestion(questionObject);
        System.out.println("( ˘◡˘) Question added!\n");

    }

    private void testUser() {
        ArrayList<Question> questions = bank.getQuestions();
        int score = 0;
        int outOf = questions.size();
        System.out.println("\n Starting Quiz!");
        int curQuestion = 1;
        for (Question q : questions) {
            System.out.println(curQuestion + ". \n" + q.getQuestionPrompt());
            String answer = input.next();
            if (answer.equalsIgnoreCase(q.getQuestionAnswer())) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect :(");
            }
            curQuestion++;
        }
        System.out.println("Your score is: " + score + "/" + outOf);
    }

    private void removeQuestion() {
        if (bank.isEmpty()) {
            System.out.println("No questions to delete!");
        } else {
            bank.removeQuestion();
            System.out.println("(ಠ_ಠ) Deleted most recent question!\n");
        }
    }

    private void viewQuestions() {
        if (bank.isEmpty()) {
            System.out.println("( ͡ з ͡) Add some questions!\n");
        } else {
            ArrayList<Question> questions = bank.getQuestions();
            int questionNum = 1;
            for (Question q : questions) {
                System.out.println(questionNum + ".\n");
                System.out.println("\tq: " + q.getQuestionPrompt() + "\n");
                System.out.println("\ta: " + q.getQuestionAnswer() + "\n");
                questionNum++;
            }


        }
    }

    // EFFECTS: saves the workroom to file

    private void saveQuestionBank() {
        try {
            jsonWriter.open();
            jsonWriter.write(bank);
            jsonWriter.close();
            System.out.println("Saved " + bank.getBankName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads QuestionBank from file

    public static void loadQuestionBank() throws IOException {
        try {
            bank = jsonReader.read();
            System.out.println("Loaded " + bank.getBankName() + " from " + JSON_STORE);
        } catch (IOException e) {
            throw new IOException();
        }
    }

    public static QuestionBank getBank() {
        return bank;
    }


}