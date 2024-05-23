package main.GUI;
import main.BasicClasses.*;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Map;

/**
 * The TradeWindow class represents a window for trading resources.
 */
public class TradeWindow {
    private Player player;
    private Builder builder;

    /**
     * Constructs a new TradeWindow with the given parameters.
     *
     * @param player The player.
     * @param builder The builder.
     */
    public TradeWindow(Player player, Builder builder) {
        this.player = player;
        this.builder = builder;
    }

    public void show() {
        Stage stage = new Stage();
        stage.setTitle("Trade");

        ListView<HBox> playerResources = new ListView<>();
        for (Map.Entry<String, Integer> entry : player.getResources().entrySet()) {
            Button sellButton = new Button("Sell");
            sellButton.setOnAction(e -> {
                String resourceToSell = entry.getKey();
                Map<String, Integer> prices = builder.getSellPrice(resourceToSell);
                SellDialog sellDialog = new SellDialog(player, resourceToSell, prices, builder);
                sellDialog.show();
            });
            HBox hbox = new HBox(new Label(entry.getKey() + ": " + entry.getValue()), sellButton);
            playerResources.getItems().add(hbox);
        }

        ListView<HBox> builderResources = new ListView<>();
        for (Map.Entry<String, Integer> entry : builder.getResources().entrySet()) {
            Button buyButton = new Button("Buy");
            buyButton.setOnAction(e -> {
                String resourceToBuy = entry.getKey();
                Map<String, Integer> prices = builder.getResourcePrice(resourceToBuy);
                BuyDialog buyDialog = new BuyDialog(player, resourceToBuy, prices, builder);
                buyDialog.show();
            });
            HBox hbox = new HBox(new Label(entry.getKey() + ": " + entry.getValue()), buyButton);
            builderResources.getItems().add(hbox);
        }

        VBox playerBox = new VBox(new Label("Player Resources"), playerResources);
        VBox builderBox = new VBox(new Label("Builder Resources"), builderResources);

        HBox hbox = new HBox(playerBox, builderBox);

        Scene scene = new Scene(hbox, 300, 200);
        stage.setScene(scene);

        stage.show();
    }
}