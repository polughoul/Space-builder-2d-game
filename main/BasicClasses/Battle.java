package main.BasicClasses;

public class Battle {
    private Player player;
    private SpaceBanditNpc enemy;

    public Battle(Player player, SpaceBanditNpc enemy) {
        this.player = player;
        this.enemy = enemy;
    }

    public void startBattle() {
        // battle logic
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public SpaceBanditNpc getEnemy() {
        return enemy;
    }

    public void setEnemy(SpaceBanditNpc enemy) {
        this.enemy = enemy;
    }
}