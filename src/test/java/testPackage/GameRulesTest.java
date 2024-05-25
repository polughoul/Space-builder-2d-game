package testPackage;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import main.BasicClasses.Building;
import main.BasicClasses.Planet;
import main.BasicClasses.SpaceObject;
import main.Controller.gameRules;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

class GameRulesTest {
    @Test
    void testCheckVictory() {
        gameRules gameRules = new gameRules();
        List<SpaceObject> spaceObjects = mock(List.class);
        Planet planet = mock(Planet.class);
        Building building = mock(Building.class);
        Iterator<SpaceObject> iterator = mock(Iterator.class);

        when(spaceObjects.iterator()).thenReturn(iterator);
        when(iterator.hasNext()).thenReturn(true, false);
        when(iterator.next()).thenReturn(planet);
        when(planet.getBuildings()).thenReturn(Arrays.asList(building, building, building));

        boolean result = gameRules.checkVictory(spaceObjects);

        assertTrue(result);
    }
}