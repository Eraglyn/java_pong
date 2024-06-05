package game.scene;

import engine.Engine;
import engine.event.InputEvent;
import engine.event.InputEventKey;
import engine.math.Rect2;
import engine.math.Vector2;
import game.model.Ball;
import game.model.Player;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class CourtScene extends engine.GameScene {
    private final static double xMargin = 50.0; // x margin of the game court
    private Button pauseBackButton;
    private Label scoreJ1, scoreJ2;
    private final static Vector2 courtSize = new Vector2(1000, 600); // size of the game court
    private final static Rect2 courtRect = new Rect2(Vector2.ZERO.duplicate(), courtSize); // Rect2 of the game court

    // Return the size of the game court
    public static Vector2 getCourtSize() {
        return courtSize;
    }

    // Return the Rect2 of the game court, used to check if a point is inside the
    // game court
    public static Rect2 getCourtRect() {
        return courtRect;
    }

    private void pauseGame() {
        Engine.getSingleton().setPaused(!Engine.getSingleton().isPaused());
        pauseBackButton.setVisible(Engine.getSingleton().isPaused());
    }

    private void initBackground() {
        BackgroundImage myBI = new BackgroundImage(
                new Image("file:src/main/resources/Images/fondInGame.png", 1000, 600, false, true),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        getCanvas().setBackground(new Background(myBI));
    }

    private void initGameObjects(boolean isSinglePlayer) {
        // create a player on the left side of the game court, with id 0
        addChild(new Player(0, xMargin - Player.getRacketSize().x, Color.MAGENTA,false));
        // create a player on the right side of the game court, with id 1
        addChild(new Player(1, getCourtSize().x - xMargin, Color.CYAN,isSinglePlayer));
        // create a ball in the middle of the game court
        addChild(new Ball(new Vector2(getCourtSize().x / 2.0, getCourtSize().y / 2.0)));
    }

    private void initPauseBackButton() {
        pauseBackButton = new Button("Exit (Q)");
        // ajout du style des boutons
        pauseBackButton.setStyle("-fx-background-color: magenta; -fx-text-fill: cyan; -fx-font-size: 16pt;");
        pauseBackButton.setMinSize(200, 50);
        pauseBackButton.setOnAction(e -> Engine.getSingleton().changeScene(new MainMenuScene()));
        pauseBackButton.setVisible(false);
        pauseBackButton.setLayoutX(400);
        pauseBackButton.setLayoutY(275);
        pauseBackButton.setOnMouseEntered(e -> {
            pauseBackButton.setPrefSize(205, 55);
            pauseBackButton.setLayoutX(397.5);
            pauseBackButton.setLayoutY(272.5);
        });
        pauseBackButton.setOnMouseExited(e -> {
            pauseBackButton.setPrefSize(200, 50);
            pauseBackButton.setLayoutX(400);
            pauseBackButton.setLayoutY(275);
        });

        getCanvas().getChildren().add(pauseBackButton);
    }

    private void initScoreLabel() {
        scoreJ1 = new Label("0");
        scoreJ1.setFont(new Font(40));
        scoreJ1.setLayoutX(339);
        scoreJ1.setLayoutY(22);
        scoreJ1.setStyle("-fx-text-fill: cyan");

        scoreJ2 = new Label("0");
        scoreJ2.setFont(new Font(40));
        scoreJ2.setLayoutX(638);
        scoreJ2.setLayoutY(22);
        scoreJ2.setStyle("-fx-text-fill: cyan");

        getCanvas().getChildren().addAll(scoreJ1, scoreJ2);
    }

    private void updateScoreLabel() {
        scoreJ1.setText(Player.getPlayerById(0).getScore() + "");
        scoreJ2.setText(Player.getPlayerById(1).getScore() + "");
    }

    @Override
    protected void onInput(InputEvent event) {
        super.onInput(event);
        InputEventKey evKey = (InputEventKey) event; // cast the event to a key input event
        if (evKey.isPressed()) {
            if (evKey.getKey() == KeyCode.ESCAPE) {
                pauseGame();
            } else if (Engine.getSingleton().isPaused() && evKey.getKey() == KeyCode.Q) {
                Engine.getSingleton().changeScene(new MainMenuScene());
            }
        }
    }

    @Override
    protected void onUpdate(double delta) {
        super.onUpdate(delta);
        updateScoreLabel();
    }

    public CourtScene(boolean isSinglePlayer) {
        super(new Pane());
        getCanvas().setMinSize(getCourtSize().x, getCourtSize().y);
        initBackground();
        initPauseBackButton();
        initScoreLabel();
        initGameObjects(isSinglePlayer);
        Engine.getSingleton().setPaused(false);
    }
}
