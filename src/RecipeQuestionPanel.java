import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*
 * Author:
 * Go, Mardelito Tutor
 * Joshua Famor
 * BSCS - A121
 * BrewAcademy
 */

class RecipeQuestionPanel extends JPanel {
    private String[] correctIngredients;
    private JPanel dropTargetPanel;
    private String questionTitle;

    public RecipeQuestionPanel(String drinkName, String[] correctIngredients, String[] allIngredients) {
        questionTitle = drinkName;
        this.correctIngredients = correctIngredients;
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        JPanel drinkNamePanel = new JPanel();
        drinkNamePanel.setLayout(new BorderLayout());
        drinkNamePanel.setBackground(Color.WHITE);

        JLabel drinkNameLabel = new JLabel(drinkName + " Recipe");
        drinkNameLabel.setFont(new Font("Arial", Font.BOLD, 25));
        drinkNameLabel.setForeground(Color.BLACK);
        drinkNameLabel.setBorder(new RoundedBorder(10, Color.LIGHT_GRAY));
        drinkNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        drinkNamePanel.add(drinkNameLabel, BorderLayout.NORTH);

        JPanel instructionPanel = new JPanel();
        instructionPanel.setLayout(new BorderLayout());

        instructionPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel instructionLabel = new JLabel("Drag an ingredient from the 'Choices' section and drop it into the empty boxes to complete the recipe.");
        instructionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        instructionLabel.setForeground(Color.BLACK);
        instructionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        instructionPanel.add(instructionLabel, BorderLayout.CENTER);


        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBackground(Color.WHITE);
        headerPanel.add(drinkNamePanel);
        headerPanel.add(Box.createVerticalStrut(10));
        headerPanel.add(instructionPanel);

        add(headerPanel, BorderLayout.NORTH);

        dropTargetPanel = new JPanel();
        dropTargetPanel.setLayout(new BoxLayout(dropTargetPanel, BoxLayout.Y_AXIS));
        dropTargetPanel.setBackground(Color.WHITE);
        dropTargetPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        for (int i = 0; i < correctIngredients.length; i++) {
            JTextField textField = new JTextField("Empty Box " + (i + 1));
            textField.setTransferHandler(new TransferHandler("text"));
            textField.setPreferredSize(new Dimension(100, 25));
            textField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            textField.setEditable(false);
            textField.setFont(new Font("Arial", Font.BOLD, 30));
            textField.setHorizontalAlignment(JTextField.CENTER);
            dropTargetPanel.add(textField);
            dropTargetPanel.add(Box.createVerticalStrut(5));
        }
        add(dropTargetPanel, BorderLayout.CENTER);

        JPanel dragSourcePanel = new JPanel();
        dragSourcePanel.setLayout(new BoxLayout(dragSourcePanel, BoxLayout.Y_AXIS));
        dragSourcePanel.setBackground(Color.WHITE);
        dragSourcePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel choicesLabel = new JLabel("Choices");
        choicesLabel.setFont(new Font("Arial", Font.BOLD, 20));
        choicesLabel.setBorder(new RoundedBorder(10, Color.LIGHT_GRAY));
        choicesLabel.setPreferredSize(new Dimension(200, 30));
        choicesLabel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        choicesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        choicesLabel.setHorizontalAlignment(SwingConstants.CENTER);
        dragSourcePanel.add(choicesLabel);
        dragSourcePanel.add(Box.createVerticalStrut(5));

        for (String ingredient : allIngredients) {
            JLabel label = new JLabel(ingredient);
            label.setTransferHandler(new TransferHandler("text"));
            label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            label.setPreferredSize(new Dimension(200, 30));
            label.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setFont(new Font("Arial", Font.BOLD, 16));
            label.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    JComponent c = (JComponent) e.getSource();
                    TransferHandler handler = c.getTransferHandler();
                    handler.exportAsDrag(c, e, TransferHandler.COPY);
                }
            });
            dragSourcePanel.add(label);
            dragSourcePanel.add(Box.createVerticalStrut(5));
        }
        add(dragSourcePanel, BorderLayout.WEST);
    }

    public boolean isCorrect() {
        Component[] components = dropTargetPanel.getComponents();
        int correctCount = 0;
        int index = 0;
        for (Component c : components) {
            if (c instanceof JTextField) {
                JTextField textField = (JTextField) c;
                if (textField.getText().equals(correctIngredients[index])) {
                    correctCount++;
                }
                index++;
            }
        }
        return correctCount == correctIngredients.length;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }
}
