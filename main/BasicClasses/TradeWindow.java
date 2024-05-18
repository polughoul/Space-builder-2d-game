package main.BasicClasses;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Map;

public class TradeWindow {
    private Player player;
    private Builder builder;

    public TradeWindow(Player player, Builder builder) {
        this.player = player;
        this.builder = builder;
    }

    public void show() {
        Stage stage = new Stage();
        stage.setTitle("Trade");

        ListView<String> playerResources = new ListView<>();
        for (Map.Entry<String, Integer> entry : player.getResources().entrySet()) {
            playerResources.getItems().add(entry.getKey() + ": " + entry.getValue());
        }

        ListView<String> builderResources = new ListView<>();
        for (Map.Entry<String, Integer> entry : builder.getResources().entrySet()) {
            builderResources.getItems().add(entry.getKey() + ": " + entry.getValue());
        }

        VBox playerBox = new VBox(new Label("Player Resources"), playerResources);
        VBox builderBox = new VBox(new Label("Builder Resources"), builderResources);

        HBox hbox = new HBox(playerBox, builderBox);

        Scene scene = new Scene(hbox, 300, 200);
        stage.setScene(scene);
        stage.show();
    }
}