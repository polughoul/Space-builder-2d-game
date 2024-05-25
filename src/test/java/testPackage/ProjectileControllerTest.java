package testPackage;

import main.BasicClasses.*;
import main.Controller.Control;
import main.Controller.ProjectileController;
import main.GUI.GameView;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class ProjectileControllerTest {
    @Test
    void testUpdateProjectiles() {
        Player player = mock(Player.class);
        GameView gameView = mock(GameView.class);
        Control control = mock(Control.class);
        Projectile projectile = mock(Projectile.class);
        List<Projectile> projectiles = new ArrayList<>();
        projectiles.add(projectile);
        ProjectileController projectileController = new ProjectileController(projectiles, player, control, gameView);
        SpaceObject spaceObject = mock(Planet.class);
        Bandit bandit = mock(Bandit.class);
        List<Bandit> bandits = new ArrayList<>();
        bandits.add(bandit);
        Building building = mock(Building.class);
        List<Building> buildings = new ArrayList<>();
        buildings.add(building);

        when(player.getHealth()).thenReturn(100);
        when(gameView.getCurrentSpaceObject()).thenReturn(spaceObject);
        when(((Planet) spaceObject).getBandits()).thenReturn(bandits);
        when(((Planet) spaceObject).getBuildings()).thenReturn(buildings);
        when(bandit.getDamage()).thenReturn(10);
        when(building.getHealth()).thenReturn(100);
        when(projectile.getOwner()).thenReturn(bandit);
        when(projectile.getX()).thenReturn(0.0);
        when(projectile.getY()).thenReturn(0.0);
        when(bandit.getX()).thenReturn(0);
        when(bandit.getY()).thenReturn(0);

        projectileController.updateProjectiles();

        verify(projectile, times(1)).move();
        verify(bandit, times(2)).getDamage();
        verify(building, times(3)).getHealth();
    }
}
