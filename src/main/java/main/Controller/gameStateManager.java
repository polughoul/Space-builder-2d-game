package main.Controller;

import main.BasicClasses.GameState;

import java.io.*;
import java.util.logging.Logger;
/**
 * The gameStateManager class represents the manager of the game state.
 * It contains methods to save and load the game state.
 */
public class gameStateManager {

    private static final Logger logger = Logger.getLogger(gameStateManager.class.getName());

    /**
     * Saves the game state to a file.
     *
     * @param filename The name of the file to save the game state to.
     * @param gameState The game state to save.
     */

    public void saveGame(String filename, GameState gameState) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(gameState);
            logger.info("The game was saved in file: " + filename);
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
            logger.info("The game was loaded from file: " + filename);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return gameState;
    }
}