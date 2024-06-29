import javax.swing.*;
import java.awt.*;


public class MainProgram extends JFrame {

    public MainProgram() {
        super("BrewAcademy");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 800));
        setLayout(new BorderLayout());

        // Set icon for the application
        ImageIcon icon = new ImageIcon("bg_logo.png");
        Image image = icon.getImage();
        setIconImage(image);

        initializeComponents();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeComponents() {
        MenuPanel menuPanel = new MenuPanel(this);
        add(menuPanel, BorderLayout.CENTER);
    }

    public void startQuiz(String userName) {
        QuizPanel quizPanel = new QuizPanel(userName);
        getContentPane().removeAll();
        getContentPane().add(quizPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainProgram::new);
    }
}
