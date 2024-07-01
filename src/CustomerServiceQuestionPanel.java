import javax.swing.*;
import java.awt.*;
import java.util.Enumeration;

/*
 * Author:
 * Go, Mardelito Tutor
 * Joshua Famor
 * BSCS - A121
 * BrewAcademy
 */

class CustomerServiceQuestionPanel extends JPanel {
    private ButtonGroup group;
    private int correctOption;
    private String questionTitle;

    public CustomerServiceQuestionPanel(String question, String[] options, int correctOption) {
        this.correctOption = correctOption;
        questionTitle = question;
        setBackground(Color.WHITE);
        setLayout(new BorderLayout(10, 10));

        JLabel panelTitle = new JLabel("Customer Service");
        panelTitle.setFont(new Font("Arial", Font.BOLD, 25));
        panelTitle.setBorder(new RoundedBorder(10, Color.LIGHT_GRAY));
        panelTitle.setHorizontalAlignment(SwingConstants.CENTER);
        add(panelTitle, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(centerPanel, BorderLayout.CENTER);

        JLabel questionLabel = new JLabel(question);
        questionLabel.setFont(questionLabel.getFont().deriveFont(Font.BOLD, 20));
        centerPanel.add(questionLabel);

        group = new ButtonGroup();
        for (String option : options) {
            JRadioButton radioButton = new JRadioButton(option);
            radioButton.setFont(radioButton.getFont().deriveFont(Font.PLAIN, 16));
            group.add(radioButton);
            centerPanel.add(radioButton);
        }
    }

    public boolean isCorrect() {
        int selectedIndex = 0;
        for (Enumeration<AbstractButton> buttons = group.getElements(); buttons.hasMoreElements(); selectedIndex++) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return selectedIndex == correctOption;
            }
        }
        return false;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            String[] options = {"Option A", "Option B", "Option C", "Option D"};
            CustomerServiceQuestionPanel panel = new CustomerServiceQuestionPanel("Sample Question", options, 0);

            JFrame frame = new JFrame("Test Panel");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(panel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}