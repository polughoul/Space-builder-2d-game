package main.GUI;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        // Создаем новый фрейм (окно)
        JFrame frame = new JFrame("Game Title");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создаем панель для кнопок
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));

        // Создаем кнопки
        JButton startButton = new JButton("Start Game");
        startButton.setFocusable(false);
        JButton loadButton = new JButton("Load Game");
        loadButton.setFocusable(false);
        JButton exitButton = new JButton("Exit Game");
        exitButton.setFocusable(false);


        buttonPanel.add(startButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(exitButton);

        // Создаем игровую панель
        panel gamePanel = new panel();

        JButton returnButton = new JButton("Return to Galaxy");
        returnButton.setFocusable(false);
        returnButton.addActionListener(e -> gamePanel.setCurrentSpaceObject(null));
        buttonPanel.add(returnButton);

        // Добавляем панели на фрейм
        frame.getContentPane().add(buttonPanel, BorderLayout.NORTH);
        frame.getContentPane().add(gamePanel, BorderLayout.CENTER);

        // Устанавливаем размер фрейма и делаем его видимым
        frame.setSize(800, 600);
        frame.setVisible(true);
    }
}