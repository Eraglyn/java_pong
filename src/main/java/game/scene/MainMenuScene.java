package game.scene;

import engine.Engine;
import engine.event.InputEvent;
import engine.event.InputEventKey;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;

public class MainMenuScene extends engine.GameScene {

    private void initBackground() {
        BackgroundImage fondMenu = new BackgroundImage(
                new Image("file:src/main/resources/Images/fondPong.png", 1000, 600, false, true),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        getCanvas().setBackground(new Background(fondMenu));
    }

    private void initSinglePlayerButton() {
        Button startButton = new Button("Singleplayer (G)");
        startButton.setStyle("-fx-background-color: magenta; -fx-text-fill: cyan; -fx-font-size: 16pt;");
        startButton.setMinSize(200, 50);
        startButton.setOnAction(e -> Engine.getSingleton().changeScene(new CourtScene(true)));
        startButton.setOnMouseEntered(e -> startButton.setPrefSize(205, 55));
        startButton.setOnMouseExited(e -> startButton.setPrefSize(200, 50));
        getCanvas().getChildren().add(startButton);
    }

    private void initMultiPlayerButton() {
        Button startButton = new Button("Local Multiplayer (L)");
        startButton.setStyle("-fx-background-color: magenta; -fx-text-fill: cyan; -fx-font-size: 16pt;");
        startButton.setMinSize(250, 50);
        startButton.setOnAction(e -> Engine.getSingleton().changeScene(new CourtScene(false)));
        startButton.setOnMouseEntered(e -> startButton.setPrefSize(255, 55));
        startButton.setOnMouseExited(e -> startButton.setPrefSize(250, 50));
        getCanvas().getChildren().add(startButton);
    }

    private void initOptionButton() {
        Button optionButton = new Button("Option (O)");
        optionButton.setStyle("-fx-background-color: magenta; -fx-text-fill: cyan; -fx-font-size: 16pt;");
        optionButton.setMinSize(200, 50);
        optionButton.setOnAction(e -> Engine.getSingleton().changeScene(new OptionMenuScene()));
        optionButton.setOnMouseEntered(e -> optionButton.setPrefSize(205, 55));
        optionButton.setOnMouseExited(e -> optionButton.setPrefSize(200, 50));
        getCanvas().getChildren().add(optionButton);
    }

    @Override
    protected void onInput(InputEvent event) {
        super.onInput(event);
        InputEventKey evKey = (InputEventKey) event; // cast the event to a key input event
        if (evKey.isPressed()) {
            if (evKey.getKey() == KeyCode.G) {
                Engine.getSingleton().changeScene(new CourtScene(true));
            } else if (evKey.getKey() == KeyCode.L) {
                Engine.getSingleton().changeScene(new CourtScene(false));
            } else if (evKey.getKey() == KeyCode.O) {
                Engine.getSingleton().changeScene(new OptionMenuScene());
            }
        }
    }

    public MainMenuScene() {
        super(new VBox(10));
        VBox menuCanvas = (VBox) getCanvas();
        menuCanvas.setMinSize(1000, 600);
        menuCanvas.setAlignment(Pos.CENTER);
        initBackground();
        initSinglePlayerButton();
        initMultiPlayerButton();
        initOptionButton();
    }

}
