package main;

import javax.swing.*;


import main.BasicClasses.*;
import main.Controller.Control;
import main.GUI.panel;

import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Game game = initializeGame();

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Kosmický Stavitel");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JButton startButton = new JButton("Start Game");
            JButton loadButton = new JButton("Load Game");
            JButton exitButton = new JButton("Exit to Desktop");

            panel gamePanel = new panel(game);
            Control control = new Control(gamePanel);
            gamePanel.addKeyListener(control);
            gamePanel.setVisible(false); // Сначала делаем панель игры невидимой
            frame.getContentPane().add(gamePanel, BorderLayout.CENTER);

            startButton.addActionListener(e -> {
                gamePanel.setVisible(true); // Делаем панель игры видимой после нажатия на кнопку "Start Game"
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
            frame.setSize(1200, 800);
            frame.setResizable(false);
            frame.setVisible(true);
        });
    }

    public static Game initializeGame() {
        // Создаем ресурсы
        Resource gold = new Resource("gold", 100, 100, 100);
        Resource silver = new Resource("silver", 200, 200, 200);
        Resource wood = new Resource("wood", 300, 300, 300);
        Resource iron = new Resource("iron", 400, 400, 400);

        // Создаем списки ресурсов для астероидов и планет
        List<Resource> asteroidResources = Arrays.asList(gold, silver);
        List<Resource> planetResources = Arrays.asList(wood, iron);

        // Создаем космических строителей и космических бандитов
        SpaceBuilderNpc spaceBuilder = new SpaceBuilderNpc("Builder", 1, new HashMap<>(), 500, 200);
        SpaceBanditNpc spaceBandit = new SpaceBanditNpc("Bandit", 1, 100, 10, 600, 300);

        // Создаем списки космических строителей и космических бандитов для планет
        List<SpaceBuilderNpc> spaceBuilders = Arrays.asList(spaceBuilder);
        List<SpaceBanditNpc> spaceBandits = Arrays.asList(spaceBandit);

        // Создаем астероиды и планеты
        // В методе initializeGame()
        Asteroid asteroid1 = new Asteroid(asteroidResources, "Asteroid1", 100, 200, 200);
        Asteroid asteroid2 = new Asteroid(asteroidResources, "Asteroid2", 200, 300, 300);
        Planet planet1 = new Planet(planetResources, "Planet1", 300, spaceBuilders, spaceBandits, 400, 400);
        Planet planet2 = new Planet(planetResources, "Planet2", 400, spaceBuilders, spaceBandits, 500, 500);

        // Добавляем астероиды и планеты в список космических объектов
        List<SpaceObject> spaceObjects = Arrays.asList(asteroid1, asteroid2, planet1, planet2);

        // Создаем галактику
        Galaxy galaxy = new Galaxy("Galaxy 1", spaceObjects);

        // Создаем игрока
        Player player = new Player(1000, 100);

        // Создаем игру
        Game game = new Game(player, galaxy);

        // Здесь вы можете добавить код для запуска игры
        return game;
    }
}