package main.GUI;
import main.BasicClasses.Control;
import javax.swing.*;
import java.awt.*;

public class Gui {
    public static void createAndShowGUI() {
        // Создаем окно
        JFrame frame = new JFrame("Kosmický Stavitel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());// Устанавливаем менеджер компоновки
        frame.setSize(800, 600);
        frame.setResizable(false);

        // Добавляем кнопки
        JButton startButton = new JButton("Start Game");
        JButton loadButton = new JButton("Load Game");
        JButton exitButton = new JButton("Exit to Desktop");

        startButton.addActionListener(e -> {
            // Создаем новый GamePanel и добавляем его на окно
            panel gamePanel = new panel();
            Control control = new Control(gamePanel);
            gamePanel.addKeyListener(control);
            frame.getContentPane().add(gamePanel, BorderLayout.CENTER);
            gamePanel.requestFocusInWindow();
            frame.revalidate();
            frame.repaint();
        });

        exitButton.addActionListener(e -> System.exit(0));

        // Добавляем кнопки на панель
        JPanel panel = new JPanel();
        panel.setBackground(Color.GRAY);
        panel.add(startButton);
        panel.add(loadButton);
        panel.add(exitButton);

        frame.getContentPane().add(panel, BorderLayout.NORTH);

        // Отображаем окно
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Запускаем создание GUI в потоке диспетчера событий
        SwingUtilities.invokeLater(Gui::createAndShowGUI);
    }
}
