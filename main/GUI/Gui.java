package main.GUI;
import main.BasicClasses.Control;
import javax.swing.*;
import java.awt.*;

public class Gui {
    public static void createAndShowGUI() {
        JFrame frame = new JFrame("Kosmický Stavitel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());// Устанавливаем менеджер компоновки
        frame.setSize(800, 600);
        frame.setResizable(false);
        JButton startButton = new JButton("Start Game");
        JButton loadButton = new JButton("Load Game");
        JButton exitButton = new JButton("Exit to Desktop");

        startButton.addActionListener(e -> {
            panel gamePanel = new panel();
            Control control = new Control(gamePanel);
            gamePanel.addKeyListener(control);
            frame.getContentPane().add(gamePanel, BorderLayout.CENTER);
            gamePanel.requestFocusInWindow();
            frame.revalidate();
            frame.repaint();
        });

        exitButton.addActionListener(e -> System.exit(0));
        JPanel panel = new JPanel();
        panel.setBackground(Color.GRAY);
        panel.add(startButton);
        panel.add(loadButton);
        panel.add(exitButton);

        frame.getContentPane().add(panel, BorderLayout.NORTH);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Gui::createAndShowGUI);
    }
}
