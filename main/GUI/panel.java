package main.GUI;

import main.Controller.Control;
import main.BasicClasses.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class panel extends JPanel {
    private Player player;
    private Control control;
    private Timer timer;
    private List<SpaceObject> spaceObjects;
    private SpaceObject currentSpaceObject;


    public panel() {
        player = new Player(100, 100); // создаем игрока с начальными параметрами
        control = new Control(player, this); // создаем контроллер, передавая в него игрока
        addKeyListener(control); // добавляем контроллер как слушателя клавиатуры
        setFocusable(true); // делаем панель способной получать фокус, чтобы она могла принимать ввод с клавиатуры
        requestFocusInWindow(); // Запрашиваем фокус ввода в окне

        // Создаем таймер для игрового цикла
        timer = new Timer(1000 / 60, e -> {
            control.movePlayer(); // Перемещаем игрока
            repaint(); // Перерисовываем панель
        });
        timer.start(); // Запускаем таймер

        spaceObjects = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            spaceObjects.add(new Asteroid( "Asteroid" + i, 20, random.nextInt(800), random.nextInt(600)));
        }
        for (int i = 0; i < 3; i++) {
            spaceObjects.add(new Planet(new ArrayList<>(), "Planet" + i, 40, new ArrayList<>(), new ArrayList<>(), random.nextInt(800), random.nextInt(600)));
        }
    }
    public void setCurrentSpaceObject(SpaceObject spaceObject) {
        this.currentSpaceObject = spaceObject;
    }


    public SpaceObject getCurrentSpaceObject() {
        return currentSpaceObject;
    }

    public List<SpaceObject> getSpaceObjects() {
        return spaceObjects;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Рисуем игрока
        g.fillRect(player.getX(), player.getY(), 40, 40);
        // Если игрок находится на космическом объекте, рисуем объекты на этом космическом объекте
        if (currentSpaceObject != null && currentSpaceObject instanceof Asteroid) {
            List<Resource> resources = currentSpaceObject.getResources();
            if (resources != null) {
                for (Resource resource : resources) {
                    resource.draw(g);
                }
            }
        } else {
            // Иначе рисуем космические объекты
            for (SpaceObject spaceObject : spaceObjects) {
                g.drawOval(spaceObject.getX(), spaceObject.getY(), spaceObject.getSize(), spaceObject.getSize());
            }
        }
    }

    // ... остальной код ...
}