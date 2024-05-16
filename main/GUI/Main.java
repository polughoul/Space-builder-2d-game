package main.GUI;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Kosmický stavitel");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));

        JButton startButton = new JButton("Start Game");
        startButton.setFocusable(false);
        JButton loadButton = new JButton("Load Game");
        loadButton.setFocusable(false);
        JButton exitButton = new JButton("Exit Game");
        exitButton.setFocusable(false);

        buttonPanel.add(startButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(exitButton);

        panel gamePanel = new panel();
        gamePanel.setVisible(false);

        JButton returnButton = new JButton("Return to Galaxy");
        returnButton.setFocusable(false);
        returnButton.addActionListener(e -> gamePanel.setCurrentSpaceObject(null));
        buttonPanel.add(returnButton);

        frame.getContentPane().add(buttonPanel, BorderLayout.NORTH);
        frame.getContentPane().add(gamePanel, BorderLayout.CENTER);

        startButton.addActionListener(e -> {
            gamePanel.setVisible(true);
            gamePanel.requestFocusInWindow();
        });

        exitButton.addActionListener(e -> {
            System.exit(0);
        });

        frame.setSize(1280, 960);
        frame.setVisible(true);
        frame.setResizable(false);
    }
}