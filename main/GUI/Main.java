package main.GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Kosmický stavitel");

        HBox buttonPanel = new HBox();

        Button startButton = new Button("Start Game");
        Button loadButton = new Button("Load Game");
        Button exitButton = new Button("Exit Game");

        buttonPanel.getChildren().addAll(startButton, loadButton, exitButton);

        GameView gameGameView = new GameView();
        gameGameView.setVisible(false);

        Button returnButton = new Button("Return to Galaxy");
        HBox.setHgrow(startButton, Priority.ALWAYS);
        HBox.setHgrow(loadButton, Priority.ALWAYS);
        HBox.setHgrow(exitButton, Priority.ALWAYS);
        HBox.setHgrow(returnButton, Priority.ALWAYS);
        returnButton.setOnAction(e -> {
            gameGameView.setCurrentSpaceObject(null);
            gameGameView.requestFocus();
        });

        buttonPanel.getChildren().add(returnButton);

        BorderPane root = new BorderPane();
        root.setTop(buttonPanel);
        root.setCenter(gameGameView);

        startButton.setOnAction(e -> {
            gameGameView.setVisible(true);
            gameGameView.requestFocus();
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