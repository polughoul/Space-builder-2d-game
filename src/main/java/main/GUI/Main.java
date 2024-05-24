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
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * The Main class is the entry point of the application.
 * It extends the Application class from JavaFX.
 */
public class Main extends Application {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    private static String[] launchArgs;

    public static void main(String[] args) {
        launchArgs = args;
        launch(args);
    }

    /**
     * The start method that is called after the application is launched.
     *
     * @param primaryStage The primary stage for this application.
     */
    @Override
    public void start(Stage primaryStage) {
        boolean loggingEnabled = true;

        // Проверяем аргументы командной строки
        for (String arg : launchArgs) {
            if ("nologging".equals(arg)) {
                loggingEnabled = false;
                break;
            }
        }

        Logger rootLogger = Logger.getLogger("");
        if (loggingEnabled) {
            rootLogger.setLevel(Level.ALL);
        } else {
            rootLogger.setLevel(Level.OFF);
        }
        primaryStage.setTitle("Kosmický stavitel");
        BorderPane root = new BorderPane();
        Image image = new Image("file:src/main/java/main/assets/BG.png");

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
        logger.info("Application started");
    }
}