package main.Controller;

import main.BasicClasses.*;
import java.io.*;

public class gameStateManager {

    public void saveGame(String filename, GameState gameState) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(gameState);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public GameState loadGame(String filename) {
        GameState gameState = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            gameState = (GameState) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return gameState;
    }
}