package testPackage;

import main.BasicClasses.*;
import main.Controller.Control;
import main.GUI.GameView;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.*;

public class ControlTest {
    @Test
    void testCollectResource() {
        Player player = mock(Player.class);
        GameView gameView = mock(GameView.class);
        SpaceObject spaceObject = mock(Planet.class);
        Resource resource = mock(Resource.class);
        Control control = new Control(player, gameView);

        when(gameView.getCurrentSpaceObject()).thenReturn(spaceObject);
        when(spaceObject.getResources()).thenReturn(new ArrayList<>(Arrays.asList(resource)));
        when(resource.isPlayerOnResource(anyInt(), anyInt())).thenReturn(true);

        control.collectResource();

        verify(player, times(1)).collectResource(resource);
        verify(spaceObject, times(1)).removeResource(resource);
    }

    @Test
    void testMovePlayer() {
        Player player = mock(Player.class);
        GameView gameView = mock(GameView.class);
        SpaceObject spaceObject = mock(SpaceObject.class);
        Control control = new Control(player, gameView);

        when(gameView.getSpaceObjects()).thenReturn(new ArrayList<>(Arrays.asList(spaceObject)));
        when(spaceObject.isPlayerOnObject(anyInt(), anyInt())).thenReturn(true);

        control.moveUp = true;
        control.moveDown = true;
        control.moveLeft = true;
        control.moveRight = true;

        control.movePlayer();

        verify(player, times(1)).moveUp();
        verify(player, times(1)).moveDown();
        verify(player, times(1)).moveLeft();
        verify(player, times(1)).moveRight();
        verify(gameView, times(1)).setCurrentSpaceObject(spaceObject);
    }
}