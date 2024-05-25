package testPackage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import main.BasicClasses.GameState;
import main.Controller.gameStateManager;

class GameStateManagerTest {
    @Test
    void testSaveAndLoadGame() {
        gameStateManager gameStateManager = new gameStateManager();
        GameState gameState = mock(GameState.class);

        gameStateManager.saveGame("testSave.ser", gameState);

        GameState loadedGameState = gameStateManager.loadGame("testSave.ser");

        assertNotNull(loadedGameState);
    }
}
