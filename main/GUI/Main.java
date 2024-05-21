package main.GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Kosmický stavitel");
        BorderPane root = new BorderPane();
        Image image = new Image("file:main/assets/BG.png");

        ImageView imageView = new ImageView(image);

        imageView.setFitWidth(1280);
        imageView.setFitHeight(960);
        imageView.setX(0);
        imageView.setY(0);
        root.getChildren().add(imageView);

        HBox buttonPanel = new HBox();

        Button startButton = new Button("Start Game");
        Button saveButton = new Button("Save Game");
        Button loadButton = new Button("Load Game");
        Button exitButton = new Button("Exit Game");

        buttonPanel.getChildren().addAll(startButton, loadButton, exitButton, saveButton);

        GameView gameGameView = new GameView();
        gameGameView.setVisible(false);

        Button returnButton = new Button("Return to Galaxy");
        HBox.setHgrow(startButton, Priority.ALWAYS);
        HBox.setHgrow(saveButton, Priority.ALWAYS);
        HBox.setHgrow(loadButton, Priority.ALWAYS);
        HBox.setHgrow(exitButton, Priority.ALWAYS);
        HBox.setHgrow(returnButton, Priority.ALWAYS);
        returnButton.setOnAction(e -> {
            gameGameView.setCurrentSpaceObject(null);
            gameGameView.requestFocus();
        });

        buttonPanel.getChildren().add(returnButton);

        root.setTop(buttonPanel);
        root.setCenter(gameGameView);

        startButton.setOnAction(e -> {
            gameGameView.setVisible(true);
            gameGameView.requestFocus();
        });

        saveButton.setOnAction(e -> {
            gameGameView.saveGame("savegame.ser");
        });

        loadButton.setOnAction(e -> {
            gameGameView.loadGame("savegame.ser");
        });


        exitButton.setOnAction(e -> {
            System.exit(0);
        });


        Scene scene = new Scene(root, 1280, 960);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}