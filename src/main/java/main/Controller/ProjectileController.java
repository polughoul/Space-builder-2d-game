package main.Controller;

import javafx.application.Platform;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import main.BasicClasses.*;
import main.GUI.GameView;


/**
 * The ProjectileController class is responsible for controlling the projectiles in the game.
 */
public class ProjectileController {
    private List<Projectile> projectiles;
    private Player player;
    private Control control;
    private GameView gameView;
    private Planet planet;
    private boolean isPlayerAlive = true;
    private static final Logger logger = Logger.getLogger(ProjectileController.class.getName());

    /**
     * Constructs a new ProjectileController with the given parameters.
     *
     * @param projectiles The list of projectiles.
     * @param player The player.
     * @param control The control.
     * @param gameView The game view.
     */
    public ProjectileController(List<Projectile> projectiles, Player player, Control control, GameView gameView) {
        this.projectiles = projectiles;
        this.player = player;
        this.control = control;
        this.gameView = gameView;
    }

    /**
     * Sets the player's alive status.
     *
     * @param isPlayerAlive The player's alive status.
     */
    public void setPlayerAlive(boolean isPlayerAlive) {
        this.isPlayerAlive = isPlayerAlive;
    }

    /**
     * Updates the projectiles in the game.
     */
    public void updateProjectiles() {
        if (!isPlayerAlive || player.getHealth() <= 0) {
            isPlayerAlive = false;
            return;
        }

        Iterator<Projectile> iterator = projectiles.iterator();
        while (iterator.hasNext()) {
            Projectile projectile = iterator.next();
            projectile.move();

            SpaceObject currentSpaceObject = gameView.getCurrentSpaceObject();
            if (currentSpaceObject instanceof Planet) {
                handlePlanetProjectiles((Planet) currentSpaceObject, projectile, iterator);
            }
        }
    }

    /**
     * Handles the projectiles on a planet.
     *
     * @param planet The planet.
     * @param projectile The projectile.
     * @param iterator The iterator of the projectiles.
     */
    private void handlePlanetProjectiles(Planet planet, Projectile projectile, Iterator<Projectile> iterator) {
        boolean isProjectileRemoved = handleBanditProjectiles(planet.getBandits(), projectile, iterator);
        if (!isProjectileRemoved) {
            handlePlayerProjectiles(planet.getBandits(), projectile, iterator);
        }
        handleBuildingProjectiles(planet.getBuildings(), projectile, iterator);
    }

    /**
     * Handles the projectiles on bandits.
     *
     * @param bandits The list of bandits.
     * @param projectile The projectile.
     * @param iterator The iterator of the projectiles.
     * @return True if the projectile was removed, false otherwise.
     */
    private boolean handleBanditProjectiles(List<Bandit> bandits, Projectile projectile, Iterator<Projectile> iterator) {
        for (Bandit bandit : bandits) {
            if (Math.hypot(projectile.getX() - bandit.getX(), projectile.getY() - bandit.getY()) < 10) {
                if (projectile.getOwner() != bandit) {
                    iterator.remove();
                    bandit.setHealth(bandit.getHealth() - player.getDamage());
                    logger.info("Bandit was hit. Bandit's health: " + bandit.getHealth());
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Handles the projectiles on the player.
     *
     * @param bandits The list of bandits.
     * @param projectile The projectile.
     * @param iterator The iterator of the projectiles.
     */
    private void handlePlayerProjectiles(List<Bandit> bandits, Projectile projectile, Iterator<Projectile> iterator) {
        for (Bandit bandit : bandits) {
            if (Math.hypot(projectile.getX() - player.getX(), projectile.getY() - player.getY()) < 10) {
                if (projectile.getOwner() == bandit) {
                    iterator.remove();
                    player.setHealth(player.getHealth() - bandit.getDamage());
                    logger.info("You were hit. Your health: " + player.getHealth());
                    if (player.getHealth() <= 0) {
                        Platform.runLater(() -> {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION, "You died! The game will now restart.", ButtonType.OK);
                            alert.showAndWait();
                            if (alert.getResult() == ButtonType.OK) {
                                control.resetGame(gameView);
                            }
                        });
                    }
                    break;
                }
            }
        }
    }

    /**
     * Handles the projectiles on buildings.
     *
     * @param buildings The list of buildings.
     * @param projectile The projectile.
     * @param iterator The iterator of the projectiles.
     */
    private void handleBuildingProjectiles(List<Building> buildings, Projectile projectile, Iterator<Projectile> iterator) {
        for (Building building : buildings) {
            if (Math.hypot(projectile.getX() - building.getX(), projectile.getY() - building.getY()) < 10) {
                if (projectile.getOwner() instanceof Bandit) {
                    iterator.remove();
                    building.setHealth(building.getHealth() - ((Bandit) projectile.getOwner()).getDamage());
                    logger.info("Building was hit. Building's health: " + building.getHealth());
                    if (building.getHealth() <= 0) {
                        planet.removeBuilding(building);
                        planet.addAvailableBuilding(building);
                    }
                    break;
                }
            }
        }
    }
}
