package main.GUI;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import main.BasicClasses.*;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Map;

/**
 * The SellDialog class represents a dialog for selling resources.
 */

public class SellDialog {
    private Builder builder;
    private Player player;
    private String resourceToTrade;
    private Map<String, Integer> prices;

    /**
     * Constructs a new SellDialog with the given parameters.
     *
     * @param player The player.
     * @param resourceToTrade The resource to trade.
     * @param prices The prices of the resources.
     * @param builder The builder.
     */

    public SellDialog(Player player, String resourceToTrade, Map<String, Integer> prices, Builder builder) {
        this.player = player;
        this.resourceToTrade = resourceToTrade;
        this.prices = prices;
        this.builder = builder;
    }

    public void show() {
        Stage stage = new Stage();
        stage.setTitle("Sell " + resourceToTrade);

        Label playerMoneyLabel = new Label("You have " + player.getMoney() + " coins");
        Label builderMoneyLabel = new Label("Builder has " + builder.getMoney() + " coins");

        ListView<String> playerResources = new ListView<>();
        for (Map.Entry<String, Integer> entry : prices.entrySet()) {
            playerResources.getItems().add(entry.getKey() + ": " + entry.getValue());
        }

        Button tradeButton = new Button("Sell");
        tradeButton.setOnAction(e -> {
            String selectedCurrency = playerResources.getSelectionModel().getSelectedItem().split(": ")[0];
            int price = prices.get(selectedCurrency);
            int playerResourceCount = player.getResources().get(resourceToTrade);

            if (playerResourceCount > 0 && builder.getMoney() >= price) {
                player.getResources().put(resourceToTrade, playerResourceCount - 1);
                player.setMoney(player.getMoney() + price);
                builder.getResources().put(resourceToTrade, builder.getResources().getOrDefault(resourceToTrade, 0) + 1);
                builder.setMoney(builder.getMoney() - price);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                if (playerResourceCount <= 0) {
                    alert.setContentText("You do not have any " + resourceToTrade + " to sell.");
                } else {
                    alert.setContentText("The builder does not have enough money to buy " + resourceToTrade + ".");
                }
                alert.showAndWait();
            }
        });

        VBox vbox = new VBox(new Label("Select a resource to sell for " + resourceToTrade), playerMoneyLabel, builderMoneyLabel, playerResources, tradeButton);

        Scene scene = new Scene(vbox, 300, 200);
        stage.setScene(scene);

        stage.show();
    }
}