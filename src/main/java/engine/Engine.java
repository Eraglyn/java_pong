package engine;

import javafx.animation.AnimationTimer;
import javafx.stage.Stage;

/**
 * The main class of the engine.
 * It is responsible for initializing the engine and starting the game loop.
 * PLEASE DO NOT ADD ANY GAME UNIQUE LOGIC TO THIS CLASS. DO IT IN THE GAME
 * CLASS/GAME PACKAGE.
 *
 * @author See suivi.org file
 * @version 1.0
 */

public class Engine {
    // class parameters
    private GameScene currentScene; // the current game scene
    private final Stage gameStage; // the stage of the game
    private boolean isPaused = false; // whether the game is paused

    private GameScene queuedScene = null; // the scene to be switched to after the current scene is finished
    private static Engine singleton = null; // singleton instance of the engine


    public boolean isPaused() {
        return isPaused;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }

    public GameScene getCurrentScene() {
        return currentScene;
    }

    public void changeScene(GameScene scene) {
        if (scene == null) {
            throw new IllegalArgumentException("The scene cannot be null");
        }
        queuedScene = scene;
    }

    // Initialize the engine by the given stage and canvas size
    public static Engine initialize(Stage stage, GameScene scene) {
        // If the engine is already initialized, return the singleton instance,
        // otherwise create a new instance
        if (singleton == null) {
            singleton = new Engine(stage, scene);
        }
        return singleton;
    }

    // Returns the singleton instance of the engine
    public static Engine getSingleton() {
        return singleton;
    }

    // Constructor of the engine by the given stage and canvas size
    public Engine(Stage stage, GameScene mainScene) {
        // set the stage of the game
        gameStage = stage;
        changeScene(mainScene);
    }

    // Start the game loop
    public void start() {
        this.gameStage.show(); // show the stage of the game

        // Create a new AnimationTimer to run the game loop
        new AnimationTimer() {
            long last = 0; // the time of the last frame

            // Called every frame to update the game
            @Override
            public void handle(long now) {
                // Initialize the last time if it is the first frame
                if (last == 0) {
                    last = now;
                    return;
                }
                if (queuedScene != null) {
                    GameScene oldScene = currentScene;
                    currentScene = queuedScene;

                    if (oldScene != null) {
                        oldScene.free();
                    }
                    gameStage.setScene(currentScene.getScene());
                    queuedScene = null;
                }
                // Calculate the time difference between the current frame and the last frame,
                // and convert it from nanoseconds to seconds
                currentScene.onUpdate((now - last) * 1.0e-9); // Call the onFrameUpdate method to update the game
                last = now; // update the last time to the current time
            }
        }.start(); // start the game loop
    }
}
