import javax.swing.*;
import java.awt.*;

/*
 * @author
 * Go, Mardelito Tutor
 * Joshua Famor
 * BSCS - A121
 * BrewAcademy
 */

public class MainProgram extends JFrame {

    //Constructor
    public MainProgram() {
        super("BrewAcademy");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 800));
        setLayout(new BorderLayout());

        //Window icon
        ImageIcon icon = new ImageIcon("bg_logo.png");
        Image image = icon.getImage();
        setIconImage(image);
        initializeComponents();

        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    //initialize components
    private void initializeComponents() {
        MenuPanel menuPanel = new MenuPanel(this);
        add(menuPanel, BorderLayout.CENTER);
    }

    //Start quiz
    public void startQuiz(String userName) {
        QuizPanel quizPanel = new QuizPanel(userName);
        getContentPane().removeAll();
        getContentPane().add(quizPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    //Main
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainProgram::new);
    }
}
