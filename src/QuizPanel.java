import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class QuizPanel extends JPanel {

    private ArrayList<RecipeQuestionPanel> questionPanels;
    private ArrayList<CustomerServiceQuestionPanel> customerServicePanels;

    public QuizPanel(String userName) {
        setLayout(new BorderLayout());
        setOpaque(false); // Transparent background

        questionPanels = new ArrayList<>();
        customerServicePanels = new ArrayList<>();

        initializeQuestions();
        setupQuizComponents(userName);
    }

    private void initializeQuestions() {
        // Coffee preparation questions
        questionPanels.add(new RecipeQuestionPanel("Latte", new String[]{"Espresso", "Steamed Milk", "Foam"}, new String[]{"Espresso", "Steamed Milk", "Foam", "Chocolate Syrup", "Whipped Cream"}));
        questionPanels.add(new RecipeQuestionPanel("Cappuccino", new String[]{"Espresso", "Steamed Milk", "Foam"}, new String[]{"Espresso", "Steamed Milk", "Foam", "Cinnamon", "Honey"}));
        questionPanels.add(new RecipeQuestionPanel("Americano", new String[]{"Espresso", "Hot Water"}, new String[]{"Espresso", "Hot Water", "Cold Milk", "Ice", "Lemon Slice"}));

        // Customer service questions
        customerServicePanels.add(new CustomerServiceQuestionPanel("What do you do if a customer is unhappy with their drink?", new String[]{
                "A: Ignore the complaint",
                "B: Offer a replacement",
                "C: Argue with the customer",
                "D: Give a discount coupon"
        }, 1));

        customerServicePanels.add(new CustomerServiceQuestionPanel("How do you greet a customer?", new String[]{
                "A: 'What do you want?'",
                "B: 'Hello, how can I help you today?'",
                "C: 'Are you going to order or not?'",
                "D: 'Hurry up, I don't have all day.'"
        }, 1));
    }

    private void setupQuizComponents(String userName) {
        CardLayout cardLayout = new CardLayout();
        JPanel questionContainer = new JPanel(cardLayout);
        questionContainer.setOpaque(false); // Transparent background

        // Add question panels
        for (RecipeQuestionPanel qp : questionPanels) {
            questionContainer.add(qp, qp.getQuestionTitle());
        }

        // Add customer service panels
        for (CustomerServiceQuestionPanel csqp : customerServicePanels) {
            questionContainer.add(csqp, csqp.getQuestionTitle());
        }

        add(questionContainer, BorderLayout.CENTER);

        // Navigation panel
        JPanel navigationPanel = new JPanel();
        navigationPanel.setBackground(Color.WHITE);
        navigationPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        // Previous button
        JButton previousButton = new JButton("Previous Question");
        previousButton.setFont(new Font("Arial", Font.BOLD, 12));
        previousButton.setForeground(Color.BLACK);
        previousButton.setBackground(Color.WHITE);
        previousButton.setBorder(new RoundedBorder(20, Color.BLACK));
        previousButton.setPreferredSize(new Dimension(146, 30));
        previousButton.addActionListener(e -> showPreviousQuestion(questionContainer, cardLayout));
        navigationPanel.add(previousButton);

        // Next button
        JButton nextButton = new JButton("Next Question");
        nextButton.setFont(new Font("Arial", Font.BOLD, 12));
        nextButton.setForeground(Color.BLACK);
        nextButton.setBackground(Color.WHITE);
        nextButton.setBorder(new RoundedBorder(20, Color.BLACK));
        nextButton.setPreferredSize(new Dimension(146, 30));
        nextButton.addActionListener(e -> showNextQuestion(questionContainer, cardLayout));
        navigationPanel.add(nextButton);

        // Submit button
        JButton submitButton = new JButton("Submit Test");
        submitButton.setFont(new Font("Arial", Font.BOLD, 12));
        submitButton.setForeground(Color.WHITE);
        submitButton.setBackground(new Color(111, 221, 159));
        submitButton.setBorder(new RoundedBorder(20, Color.WHITE));
        submitButton.setPreferredSize(new Dimension(146, 30));
        submitButton.addActionListener(e -> showResults(userName));
        navigationPanel.add(submitButton);

        add(navigationPanel, BorderLayout.NORTH);
    }

    private void showPreviousQuestion(JPanel questionContainer, CardLayout cardLayout) {
        cardLayout.previous(questionContainer);
    }

    private void showNextQuestion(JPanel questionContainer, CardLayout cardLayout) {
        cardLayout.next(questionContainer);
    }

    private void showResults(String userName) {
        int score = calculateScore();
        int total = questionPanels.size() + customerServicePanels.size();
        String resultMessage = "Your score: " + score + " out of " + total;
        JOptionPane.showMessageDialog(this, resultMessage);

        saveResultToFile(userName, score, total);
    }

    private int calculateScore() {
        int score = 0;
        for (RecipeQuestionPanel qp : questionPanels) {
            if (qp.isCorrect()) {
                score++;
            }
        }
        for (CustomerServiceQuestionPanel csqp : customerServicePanels) {
            if (csqp.isCorrect()) {
                score++;
            }
        }
        return score;
    }

    private void saveResultToFile(String name, int score, int total) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("results.txt", true))) {
            writer.write("Name: " + name + ", Score: " + score + " out of " + total);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
