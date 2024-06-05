package game;

import engine.Engine;

import game.scene.MainMenuScene;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The Game class represents the game itself, used to initialize the game , game
 * objects and start the game loop.
 * It is a subclass of Application, which means it can be run as a JavaFX
 * application.
 *
 * @author See suivi.org file
 * @version 1.0
 */

public class Game extends Application {
    // The main function of the game, used to start the game
    @Override
    public void start(Stage primaryStage) {
        // Create a new Engine object, which is the main class of the game engine
        primaryStage.setResizable(false);
        primaryStage.setTitle("PONG");
        Engine engine = Engine.initialize(primaryStage, new MainMenuScene());
        engine.start(); // show the main stage and start the game loop
    }
}
