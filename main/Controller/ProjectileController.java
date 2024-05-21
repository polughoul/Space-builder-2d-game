package main.Controller;

import javafx.application.Platform;
import java.util.Iterator;
import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import main.BasicClasses.*;
import main.GUI.GameView;

public class ProjectileController {
    private List<Projectile> projectiles;
    private Player player;
    private Control control;
    private GameView gameView;
    private boolean isPlayerAlive = true;

    public ProjectileController(List<Projectile> projectiles, Player player, Control control, GameView gameView) {
        this.projectiles = projectiles;
        this.player = player;
        this.control = control;
        this.gameView = gameView;
    }

    public void setPlayerAlive(boolean isPlayerAlive) {
        this.isPlayerAlive = isPlayerAlive;
    }

    public void updateProjectiles() {
        Iterator<Projectile> iterator = projectiles.iterator();

        if (!isPlayerAlive) {
            return;
        }
        if (player.getHealth() <= 0) {
            isPlayerAlive = false;
        }
        while (iterator.hasNext()) {
            Projectile projectile = iterator.next();
            projectile.move();

            SpaceObject currentSpaceObject = gameView.getCurrentSpaceObject();
            if (currentSpaceObject instanceof Planet) {
                Planet planet = (Planet) currentSpaceObject;
                List<Bandit> bandits = planet.getBandits();
                for (Bandit bandit : bandits) {
                    if (Math.hypot(projectile.getX() - bandit.getX(), projectile.getY() - bandit.getY()) < 10) {
                        if (projectile.getOwner() != bandit) {
                            iterator.remove();
                            bandit.setHealth(bandit.getHealth() - player.getDamage());
                            System.out.println("Bandit was hit. Bandit's health: " + bandit.getHealth());
                            break;
                        }
                    }
                    if (Math.hypot(projectile.getX() - player.getX(), projectile.getY() - player.getY()) < 10) {
                        if (projectile.getOwner() == bandit) {
                            iterator.remove();
                            player.setHealth(player.getHealth() - bandit.getDamage());
                            System.out.println("You were hit. Your health: " + player.getHealth());
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
                List<Building> buildings = planet.getBuildings();
                for (Building building : buildings) {
                    if (Math.hypot(projectile.getX() - building.getX(), projectile.getY() - building.getY()) < 10) {
                        if (projectile.getOwner() instanceof Bandit) {
                            iterator.remove();
                            building.setHealth(building.getHealth() - ((Bandit) projectile.getOwner()).getDamage());
                            System.out.println("Building was hit. Building's health: " + building.getHealth());
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
    }
}
