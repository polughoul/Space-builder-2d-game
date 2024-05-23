package main.Controller;

import main.BasicClasses.*;
import java.io.*;

/**
 * The gameStateManager class represents the manager of the game state.
 * It contains methods to save and load the game state.
 */
public class gameStateManager {

    /**
     * Saves the game state to a file.
     *
     * @param filename The name of the file to save the game state to.
     * @param gameState The game state to save.
     */

    public void saveGame(String filename, GameState gameState) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(gameState);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the game state from a file.
     *
     * @param filename The name of the file to load the game state from.
     * @return The game state loaded from the file.
     */

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