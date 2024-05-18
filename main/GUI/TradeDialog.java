//package main.GUI;
//
//import javafx.scene.Scene;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Label;
//import main.BasicClasses.*;
//import javafx.scene.control.Button;
//import javafx.scene.control.ListView;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//
//import java.util.Map;
//
//public class TradeDialog {
//    private Builder builder;
//    private Player player;
//    private String resourceToTrade;
//    private Map<String, Integer> prices;
//
//    public TradeDialog(Player player, String resourceToTrade, Map<String, Integer> prices, Builder builder) {
//        this.player = player;
//        this.resourceToTrade = resourceToTrade;
//        this.prices = prices;
//        this.builder = builder;
//    }
//
//    public void show() {
//        Stage stage = new Stage();
//        stage.setTitle("Trade " + resourceToTrade);
//
//        ListView<String> playerResources = new ListView<>();
//        for (Map.Entry<String, Integer> entry : prices.entrySet()) {
//            playerResources.getItems().add(entry.getKey() + ": " + entry.getValue());
//        }
//
//        Button tradeButton = new Button("Trade");
//        tradeButton.setOnAction(e -> {
//            String selectedCurrency = playerResources.getSelectionModel().getSelectedItem().split(": ")[0];
//            int price = prices.get(selectedCurrency);
//            int playerResourceCount = player.getResources().get(selectedCurrency);
//
//            if (playerResourceCount >= price) {
//                // У игрока достаточно ресурса, выполните обмен
//                player.getResources().put(selectedCurrency, playerResourceCount - price);
//                player.getResources().put(resourceToTrade, player.getResources().getOrDefault(resourceToTrade, 0) + 1);
//                builder.getResources().put(resourceToTrade, builder.getResources().get(resourceToTrade) - 1);
//            } else {
//                // У игрока недостаточно ресурса, отобразите сообщение об ошибке
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("Error");
//                alert.setHeaderText(null);
//                alert.setContentText("You do not have enough " + selectedCurrency + ".");
//                alert.showAndWait();
//            }
//            // Implement the logic to trade the selected currency for the resourceToTrade
//        });
//        VBox vbox = new VBox(new Label("Select a resource to trade for " + resourceToTrade), playerResources, tradeButton);
//
//        Scene scene = new Scene(vbox, 300, 200);
//        stage.setScene(scene);
//
//        stage.show();
//    }
//}