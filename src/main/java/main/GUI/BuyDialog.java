package main.GUI;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import main.BasicClasses.Builder;
import main.BasicClasses.Player;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Map;

/**
 * The BuyDialog class represents a dialog for buying resources.
 */
public class BuyDialog {
    private Builder builder;
    private Player player;
    private String resourceToTrade;
    private Map<String, Integer> prices;


    /**
     * Constructs a new BuyDialog with the given parameters.
     *
     * @param player The player.
     * @param resourceToTrade The resource to trade.
     * @param prices The prices of the resources.
     * @param builder The builder.
     */
    public BuyDialog(Player player, String resourceToTrade, Map<String, Integer> prices, Builder builder) {
        this.player = player;
        this.resourceToTrade = resourceToTrade;
        this.prices = prices;
        this.builder = builder;
    }

    public void show() {
        Stage stage = new Stage();
        stage.setTitle("Buy " + resourceToTrade);

        Label playerMoneyLabel = new Label("You have " + player.getMoney() + " coins");
        Label builderMoneyLabel = new Label("Builder has " + builder.getMoney() + " coins");

        ListView<String> playerResources = new ListView<>();
        for (Map.Entry<String, Integer> entry : prices.entrySet()) {
            playerResources.getItems().add(entry.getKey() + ": " + entry.getValue());
        }

        Button tradeButton = new Button("Buy");
        tradeButton.setOnAction(e -> {
            String selectedCurrency = playerResources.getSelectionModel().getSelectedItem().split(": ")[0];
            int price = prices.get(selectedCurrency);
            int playerResourceCount = selectedCurrency.equals("coins") ? player.getMoney() : player.getResources().getOrDefault(selectedCurrency, 0);

            if (playerResourceCount >= price) {
                if (selectedCurrency.equals("coins")) {
                    player.setMoney(playerResourceCount - price);
                } else {
                    player.getResources().put(selectedCurrency, playerResourceCount - price);
                }
                player.getResources().put(resourceToTrade, player.getResources().getOrDefault(resourceToTrade, 0) + 1);
                builder.getResources().put(resourceToTrade, builder.getResources().get(resourceToTrade) - 1);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("You do not have enough " + selectedCurrency + ".");
                alert.showAndWait();
            }
        });

        VBox vbox = new VBox(new Label("Select a resource to buy for " + resourceToTrade), playerMoneyLabel, builderMoneyLabel, playerResources, tradeButton);

        Scene scene = new Scene(vbox, 300, 200);
        stage.setScene(scene);

        stage.show();
    }
}