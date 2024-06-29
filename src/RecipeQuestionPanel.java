import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class RecipeQuestionPanel extends JPanel {
    private String[] correctIngredients;
    private JPanel dropTargetPanel;
    private String questionTitle;

    public RecipeQuestionPanel(String drinkName, String[] correctIngredients, String[] allIngredients) {
        questionTitle = drinkName;
        this.correctIngredients = correctIngredients;
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        // Add a JLabel for the drink name
        JLabel drinkNameLabel = new JLabel(drinkName + " Recipe");
        drinkNameLabel.setFont(new Font("Arial", Font.BOLD, 25));
        drinkNameLabel.setForeground(Color.BLACK);
        drinkNameLabel.setBackground(Color.BLACK);
        drinkNameLabel.setBorder(new RoundedBorder(10, Color.LIGHT_GRAY));
        drinkNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(drinkNameLabel, BorderLayout.NORTH);

        dropTargetPanel = new JPanel();
        dropTargetPanel.setLayout(new BoxLayout(dropTargetPanel, BoxLayout.Y_AXIS));
        dropTargetPanel.setBackground(Color.WHITE);
        dropTargetPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding around the drop target panel
        for (int i = 0; i < correctIngredients.length; i++) {
            JTextField textField = new JTextField("Empty Box " + (i + 1));
            textField.setTransferHandler(new TransferHandler("text"));
            textField.setPreferredSize(new Dimension(100, 25));
            textField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Add padding around text fields
            textField.setEditable(false);
            textField.setFont(new Font("Arial", Font.BOLD, 30));
            textField.setHorizontalAlignment(JTextField.CENTER);
            dropTargetPanel.add(textField);
            dropTargetPanel.add(Box.createVerticalStrut(5)); // Add vertical spacing between components
        }
        add(dropTargetPanel, BorderLayout.CENTER);

        JPanel dragSourcePanel = new JPanel();
        dragSourcePanel.setLayout(new BoxLayout(dragSourcePanel, BoxLayout.Y_AXIS));
        dragSourcePanel.setBackground(Color.WHITE);
        dragSourcePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding around the drag source panel

        // Add a JLabel for the choices section
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
            label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Add padding around labels
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
            dragSourcePanel.add(Box.createVerticalStrut(5)); // Add vertical spacing between components
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