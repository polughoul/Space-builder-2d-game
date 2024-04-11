package main.BasicClasses;

import main.GUI.panel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Control extends KeyAdapter {
    private panel gamePanel;
    public Control(panel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                gamePanel.setUpPressed(true);
                break;
            case KeyEvent.VK_S:
                gamePanel.setDownPressed(true);
                break;
            case KeyEvent.VK_A:
                gamePanel.setLeftPressed(true);
                break;
            case KeyEvent.VK_D:
                gamePanel.setRightPressed(true);
                break;
        }
        gamePanel.updateShipPosition();
        gamePanel.repaint();
    }
    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                gamePanel.setUpPressed(false);
                break;
            case KeyEvent.VK_S:
                gamePanel.setDownPressed(false);
                break;
            case KeyEvent.VK_A:
                gamePanel.setLeftPressed(false);
                break;
            case KeyEvent.VK_D:
                gamePanel.setRightPressed(false);
                break;
        }
        gamePanel.updateShipPosition();
        gamePanel.repaint();
    }
}