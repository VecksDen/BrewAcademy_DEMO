import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuPanel extends JPanel {

    private JTextField nameField;
    private JButton startButton;

    public MenuPanel(MainProgram mainProgram) {
        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();

        // Logo image
        ImageIcon logoIcon = new ImageIcon("main_txt.png");
        Image logoImage = logoIcon.getImage().getScaledInstance(400, 110, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(logoImage));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(-50, 10, 20, 10);
        add(logoLabel, gbc);

        // Name label
        JLabel nameLabel = new JLabel("Full Name:");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 29, 5, 10);
        add(nameLabel, gbc);

        // Name text field
        nameField = new JTextField(32);
        nameField.setBorder(new RoundedBorder(10, Color.BLACK));
        nameField.setPreferredSize(new Dimension(300, 40));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 10, 10, 10);
        add(nameField, gbc);

        // Start button
        startButton = new JButton("START THE TEST");
        startButton.setFont(new Font("Roboto", Font.BOLD, 20));
        startButton.setForeground(Color.WHITE);
        startButton.setBackground(Color.BLACK);
        startButton.setBorder(new RoundedBorder(20, Color.WHITE));
        startButton.setPreferredSize(new Dimension(390, 70));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(startButton, gbc);

        // Start button action listener
        startButton.addActionListener(e -> {
            String userName = nameField.getText().trim();
            if (!userName.isEmpty()) {
                mainProgram.startQuiz(userName);
            } else {
                JOptionPane.showMessageDialog(this, "Please enter your name to start the quiz.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
