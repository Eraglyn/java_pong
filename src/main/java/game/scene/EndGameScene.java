package game.scene;

import engine.Engine;
import engine.GameScene;
import engine.event.InputEvent;
import engine.event.InputEventKey;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class EndGameScene extends GameScene {
    private void initBackground() {
        getCanvas().setBackground(new Background(new BackgroundImage(
                new Image("file:src/main/resources/Images/fondInGame.png", 1000, 600, false, true),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
    }

    private void initPlayer1Label(int score) {
        Label scoreJ1Final = new Label(score + "");

        if (score >= 10) {
            scoreJ1Final.setFont(new Font(30));
            scoreJ1Final.setLayoutX(335);
            scoreJ1Final.setLayoutY(26);
        } else {
            scoreJ1Final.setFont(new Font(40));
            scoreJ1Final.setLayoutX(339);
            scoreJ1Final.setLayoutY(22);
        }

        scoreJ1Final.setStyle("-fx-text-fill: cyan");
        getCanvas().getChildren().add(scoreJ1Final);
    }

    private void initPlayer2Label(int score) {
        Label scoreJ2Final = new Label(score + "");
        if (score >= 10) {
            scoreJ2Final.setFont(new Font(30));
            scoreJ2Final.setLayoutX(635);
            scoreJ2Final.setLayoutY(26);
        } else {
            scoreJ2Final.setFont(new Font(40));
            scoreJ2Final.setLayoutX(638);
            scoreJ2Final.setLayoutY(22);
        }

        scoreJ2Final.setStyle("-fx-text-fill: cyan");
        getCanvas().getChildren().add(scoreJ2Final);
    }

    private void initVictoireLabel(int winner) {
        Label victoire = new Label("Victoire du joueur " + winner);
        victoire.setStyle("-fx-text-fill: cyan; -fx-font-size: 40pt;");
        victoire.setLayoutX(275);
        victoire.setLayoutY(200);
        getCanvas().getChildren().add(victoire);
    }

    private void initQuitButton() {
        Button quitButton = new Button("Exit to menu (Q)");
        quitButton.setStyle("-fx-background-color: magenta; -fx-text-fill: cyan; -fx-font-size: 15pt;");
        quitButton.setMinSize(200, 50);
        quitButton.setOnAction(e -> Engine.getSingleton().changeScene(new MainMenuScene()));
        quitButton.setLayoutX(400);
        quitButton.setLayoutY(300);
        quitButton.setOnMouseEntered(e -> {
            quitButton.setPrefSize(205, 55);
            quitButton.setLayoutX(397.5);
            quitButton.setLayoutY(297.5);
        });
        quitButton.setOnMouseExited(e -> {
            quitButton.setPrefSize(200, 50);
            quitButton.setLayoutX(400);
            quitButton.setLayoutY(300);
        });
        getCanvas().getChildren().add(quitButton);
    }

    @Override
    protected void onInput(InputEvent event) {
        super.onInput(event);
        InputEventKey evKey = (InputEventKey) event; // cast the event to a key input event
        if (evKey.isPressed()) {
            if (evKey.getKey() == KeyCode.Q) {
                Engine.getSingleton().changeScene(new MainMenuScene());
            }
        }
    }

    public EndGameScene(int player1Score, int player2Score, int winner) {
        super(new Pane());
        getCanvas().setPrefSize(1000, 600);
        initBackground();
        initPlayer1Label(player1Score);
        initPlayer2Label(player2Score);
        initVictoireLabel(winner);
        initQuitButton();
    }
}
