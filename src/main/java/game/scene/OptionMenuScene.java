package game.scene;

import engine.Engine;
import engine.event.InputEvent;
import engine.event.InputEventKey;
import game.model.Player;
import javafx.animation.PauseTransition;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.util.Duration;

public class OptionMenuScene extends engine.GameScene {
    GridPane container;

    private void initBackground() {
        getCanvas().setBackground(new Background(new BackgroundImage(
                new Image("file:src/main/resources/Images/fondPong.png", 1000, 600, false, true),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
    }

    private void initGrid() {
        container = new GridPane();
        container.setPrefSize(1000, 300);
        container.setHgap(20);
        container.setVgap(20);
        container.getColumnConstraints().add(new ColumnConstraints(115));
        container.getColumnConstraints().add(new ColumnConstraints(20));
        container.getColumnConstraints().add(new ColumnConstraints(70));
        getCanvas().getChildren().add(container);
        container.setLayoutX(385);
        container.setLayoutY(255);
    }

    private void initPlayer1Up(KeyCode[][] playerControls) {
        Label Player1UpText, Player1UpKey;
        Button Player1UpChangeButton;
        Player1UpText = new Label("Player 1 UP : ");
        Player1UpKey = new Label("" + playerControls[0][0]);
        Player1UpKey.setStyle("-fx-text-fill: cyan");
        Player1UpText.setStyle("-fx-text-fill: cyan");
        Player1UpChangeButton = new Button("Change");
        Player1UpChangeButton.setStyle("-fx-background-color: magenta; -fx-text-fill: cyan; -fx-font-size: 10pt;");
        Player1UpChangeButton.setOnAction(e -> {
            Player1UpKey.setText("...");
            getCanvas().setOnKeyPressed(k -> {
                if (k.getCode() != KeyCode.ESCAPE) {
                    if (k.getCode() != playerControls[0][1] && k.getCode() != playerControls[1][0]
                            && k.getCode() != playerControls[1][1]) {
                        playerControls[0][0] = k.getCode();
                    }
                }
                Player1UpKey.setText("" + playerControls[0][0]);
                remapInputEvent();
            });
        });
        GridPane.setConstraints(Player1UpText, 0, 0);
        GridPane.setConstraints(Player1UpKey, 1, 0);
        GridPane.setConstraints(Player1UpChangeButton, 2, 0);
        container.getChildren().addAll(Player1UpText, Player1UpKey, Player1UpChangeButton);
    }

    private void initPlayer1Down(KeyCode[][] playerControls) {
        Label Player1DownText, Player1DownKey;
        Button Player1DownChangeButton;
        Player1DownText = new Label("Player 1 DOWN : ");
        Player1DownKey = new Label("" + playerControls[0][1]);
        Player1DownKey.setStyle("-fx-text-fill: cyan");
        Player1DownText.setStyle("-fx-text-fill: cyan");
        Player1DownChangeButton = new Button("Change");
        Player1DownChangeButton.setStyle("-fx-background-color: magenta; -fx-text-fill: cyan; -fx-font-size: 10pt;");
        Player1DownChangeButton.setOnAction(e -> {
            Player1DownKey.setText("...");
            getCanvas().setOnKeyPressed(k -> {
                if (k.getCode() != KeyCode.ESCAPE) {
                    if (k.getCode() != playerControls[0][0] && k.getCode() != playerControls[1][0]
                            && k.getCode() != playerControls[1][1]) {
                        playerControls[0][1] = k.getCode();
                    }
                }
                Player1DownKey.setText("" + playerControls[0][1]);
                remapInputEvent();
            });
        });
        GridPane.setConstraints(Player1DownText, 0, 1);
        GridPane.setConstraints(Player1DownKey, 1, 1);
        GridPane.setConstraints(Player1DownChangeButton, 2, 1);
        container.getChildren().addAll(Player1DownText, Player1DownKey, Player1DownChangeButton);
    }

    private void initPlayer2Up(KeyCode[][] playerControls) {
        Label Player2UpText, Player2UpKey;
        Button Player2UpChangeButton;
        Player2UpText = new Label("Player 2 UP : ");
        Player2UpKey = new Label("" + playerControls[1][0]);
        Player2UpKey.setStyle("-fx-text-fill: cyan");
        Player2UpText.setStyle("-fx-text-fill: cyan");
        Player2UpChangeButton = new Button("Change");
        Player2UpChangeButton.setStyle("-fx-background-color: magenta; -fx-text-fill: cyan; -fx-font-size: 10pt;");
        Player2UpChangeButton.setOnAction(e -> {
            Player2UpKey.setText("...");
            getCanvas().setOnKeyPressed(k -> {
                if (k.getCode() != KeyCode.ESCAPE) {
                    if (k.getCode() != playerControls[0][0] && k.getCode() != playerControls[0][1]
                            && k.getCode() != playerControls[1][1]) {
                        playerControls[1][0] = k.getCode();
                    }
                }
                Player2UpKey.setText("" + playerControls[1][0]);
                remapInputEvent();
            });
        });
        GridPane.setConstraints(Player2UpText, 0, 2);
        GridPane.setConstraints(Player2UpKey, 1, 2);
        GridPane.setConstraints(Player2UpChangeButton, 2, 2);
        container.getChildren().addAll(Player2UpText, Player2UpKey, Player2UpChangeButton);
    }

    private void initPlayer2Down(KeyCode[][] playerControls) {
        Label Player2DownText, Player2DownKey;
        Button Player2DownChangeButton;
        Player2DownText = new Label("Player 2 DOWN : ");
        Player2DownKey = new Label("" + playerControls[1][1]);
        Player2DownKey.setStyle("-fx-text-fill: cyan");
        Player2DownText.setStyle("-fx-text-fill: cyan");
        Player2DownChangeButton = new Button("Change");
        Player2DownChangeButton.setStyle("-fx-background-color: magenta; -fx-text-fill: cyan; -fx-font-size: 10pt;");
        Player2DownChangeButton.setOnAction(e -> {
            Player2DownKey.setText("...");
            getCanvas().setOnKeyPressed(k -> {
                if (k.getCode() != KeyCode.ESCAPE) {
                    if (k.getCode() != playerControls[0][0] && k.getCode() != playerControls[0][1]
                            && k.getCode() != playerControls[1][0]) {
                        playerControls[1][1] = k.getCode();
                    }
                }
                Player2DownKey.setText("" + playerControls[1][1]);
                remapInputEvent();
            });
        });
        GridPane.setConstraints(Player2DownText, 0, 3);
        GridPane.setConstraints(Player2DownKey, 1, 3);
        GridPane.setConstraints(Player2DownChangeButton, 2, 3);
        container.getChildren().addAll(Player2DownText, Player2DownKey, Player2DownChangeButton);
    }

    private void initControlSave(KeyCode[][] playerControls) {
        Label savedText;
        Button saveButton;

        savedText = new Label("Controls saved !");
        savedText.setStyle("-fx-text-fill: cyan");
        savedText.setLayoutX(460);
        savedText.setLayoutY(470);
        savedText.setVisible(false);
        saveButton = new Button("Save");
        saveButton.setStyle("-fx-background-color: magenta; -fx-text-fill: cyan; -fx-font-size: 16pt;");
        saveButton.setMinSize(200, 50);
        saveButton.setOnAction(e -> {
            Player.remapPlayerControl(playerControls);
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            savedText.setVisible(true);
            pause.setOnFinished(action -> savedText.setVisible(false));
            pause.play();
        });
        saveButton.setOnMouseEntered(e -> {
            saveButton.setPrefSize(205, 55);
            saveButton.setLayoutX(287.5);
            saveButton.setLayoutY(497.5);
        });
        saveButton.setOnMouseExited(e -> {
            saveButton.setPrefSize(200, 50);
            saveButton.setLayoutX(290);
            saveButton.setLayoutY(500);
        });
        getCanvas().getChildren().addAll(savedText, saveButton);
        saveButton.setLayoutX(290);
        saveButton.setLayoutY(500);
    }

    private void initQuitButton() {
        Button quitButton;

        quitButton = new Button("Quit (Q)");
        quitButton.setStyle("-fx-background-color: magenta; -fx-text-fill: cyan; -fx-font-size: 16pt;");
        quitButton.setMinSize(200, 50);
        quitButton.setOnAction(e -> Engine.getSingleton().changeScene(new MainMenuScene()));
        quitButton.setOnMouseEntered(e -> {
            quitButton.setPrefSize(205, 55);
            quitButton.setLayoutX(507.5);
            quitButton.setLayoutY(497.5);
        });
        quitButton.setOnMouseExited(e -> {
            quitButton.setPrefSize(200, 50);
            quitButton.setLayoutX(510);
            quitButton.setLayoutY(500);
        });

        getCanvas().getChildren().add(quitButton);
        quitButton.setLayoutX(510);
        quitButton.setLayoutY(500);
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

    public OptionMenuScene() {
        super(new Pane());
        getCanvas().setPrefSize(1000, 600);
        KeyCode[][] playerControls = {{Player.getPlayerControl()[0][0], Player.getPlayerControl()[0][1]},
                {Player.getPlayerControl()[1][0], Player.getPlayerControl()[1][1]}};
        initBackground();
        initGrid();
        initPlayer1Up(playerControls);
        initPlayer1Down(playerControls);
        initPlayer2Up(playerControls);
        initPlayer2Down(playerControls);
        initControlSave(playerControls);
        initQuitButton();
    }
}
