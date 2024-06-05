package game.model;

import engine.CanvasItem;
import engine.Engine;
import engine.event.InputEvent;
import engine.event.InputEventKey;
import engine.math.Rect2;
import engine.math.Vector2;
import game.scene.CourtScene;
import game.scene.EndGameScene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

/**
 * The Player class represents a player, and it's racket in the game.
 * It is a subclass of CanvasItem, which means it can be added to the canvas of
 * the game.
 *
 * @author See suivi.org file
 * @version 1.0
 */

public class Player extends CanvasItem implements RacketController {
    private static final Vector2 racketSize = new Vector2(10.0, 100.0); // size of the racket

    // The keycode map is used to map the keycodes to the direction of the racket by
    // the player id.
    private static KeyCode[][] playerControl = {{KeyCode.E, KeyCode.D}, {KeyCode.P, KeyCode.M}};

    // The playerList is used to store all the players in the game.
    @SuppressWarnings("FieldCanBeLocal")
    private final double racketSpeed = 300.0; // speed of the racket
    private static final ArrayList<Player> playerList = new ArrayList<>();
    private int score = 0; // score of player
    private boolean isAI = false; // is the player an AI
    private RacketState rState = RacketState.IDLE; // the current move state of the racket
    private final int playerID; // the id of the player, used to determine the racket control and score etc.
    @SuppressWarnings("FieldCanBeLocal")
    private final double strength = 50;

    private void updateAIMoveState(){
        Ball ball = Ball.getBallList().get(0);
        if(ball.getDirection().x > 0){
            if(ball.getPosition().y > getPosition().y + racketSize.y / 2){
                rState = RacketState.GOING_DOWN;
            }else if(ball.getPosition().y < getPosition().y + racketSize.y / 2){
                rState = RacketState.GOING_UP;
            }else{
                rState = RacketState.IDLE;
            }
        }else{
            rState = RacketState.IDLE;
        }
    }

    // Constructor of the Player class, it takes the initial position of the racket
    // as a parameter, and the player id.
    public Player(int id, double xPos, Color color, boolean isAI) {
        super(); // call the constructor of the parent class
        playerID = id; // set the player id
        this.isAI = isAI;
        getPosition().x = xPos; // set the x position of the racket
        getPosition().y = CourtScene.getCourtSize().y / 2.0 - racketSize.y / 2.0; // set the y position of the racket
        resetScore();
        Rectangle rec = new Rectangle(); // create a rectangle shape object from javafx
        rec.setHeight(racketSize.y); // set the height of the rectangle
        rec.setWidth(racketSize.x); // set the width of the rectangle
        rec.setFill(color); // set the color of the rectangle

        setDrawShape(rec); // set the rectangle as the shape of the racket
        playerList.add(this); // add the player to the list of players
    }

    // Returns the racket size
    public static Vector2 getRacketSize() {
        return racketSize;
    }

    public static KeyCode[][] getPlayerControl() {
        return playerControl;
    }

    public static void remapPlayerControl(KeyCode[][] tab) {
        playerControl = tab;
    }

    // Loop through all the players in the game, and check if the given rectangle
    // collides with any of the racket.
    public static int checkCollision(Rect2 rect) {
        // Loop through all the players in the game
        for (Player player : playerList) {
            // Check if the given rectangle collides with the racket of the player
            if (player.getRacketRect().intersects(rect)) {
                return player.playerID; // return the player id if the racket collides with the given rectangle
            }
        }
        return -1; // return -1 if no racket collides with the given rectangle
    }

    public int getScore() {
        return this.score;
    }

    public void updateScore() {
        this.score++;
        if (getScore() >= 10) {
            Engine.getSingleton().changeScene(new EndGameScene(getPlayerById(0).getScore(), getPlayerById(1).getScore(), playerID + 1));
        }
    }

    public void resetScore() {
        this.score = 0;
    }

    // Returns the player id
    public int getPlayerID() {
        return playerID;
    }

    // renvoie l'attribut strength
    public double getStrength() {
        return strength;
    }

    // Renvoie le joueur d'index @param de la liste des joueurs
    public static Player getPlayerById(int index) {
        return playerList.get(index);
    }

    // Returns the racket state
    @Override
    public RacketState getRacketState() {
        return rState;
    }

    // Returns the racket Rect2 object of its bounding box
    public Rect2 getRacketRect() {
        return new Rect2(getPosition().duplicate(), getRacketSize().duplicate());
    }

    // Override the onInputEvent method of the GameObject class to handle the racket
    // control
    @Override
    protected void onInput(InputEvent event) {
        super.onInput(event); // call the onInputEvent method of the parent class
        if (event instanceof InputEventKey evKey) { // check if the event is a key input event
            if (isAI) return; // if the player is an AI, then return
            // Check if the key of the input event is the up key of the player id
            if (evKey.getKey() == playerControl[playerID][0]) {
                if (evKey.isPressed() || evKey.isTyped()) { // check if the key is pressed
                    rState = RacketState.GOING_UP; // set the racket state to going up
                } else {
                    rState = RacketState.IDLE; // set the racket state to idle
                }

                // Check if the key of the input event is the down key of the player id
            } else if (evKey.getKey() == playerControl[playerID][1]) {
                if (evKey.isPressed() || evKey.isTyped()) { // check if the key is pressed
                    rState = RacketState.GOING_DOWN; // set the racket state to going down
                } else {
                    rState = RacketState.IDLE; // set the racket state to idle
                }
            }
        }
    }

    // Override the update method of the GameObject class to update the racket
    // position
    @Override
    protected void onUpdate(double deltaT) {
        super.onUpdate(deltaT); // call the update method of the parent class

        if (isAI) { // if the player is an AI
            updateAIMoveState();
        }

        // check the racket state
        switch (getRacketState()) {
            case GOING_UP: // if the racket state is going up
                getPosition().y -= racketSpeed * deltaT; // move the racket up

                // Stop the racket from going out of the court if the y position is less than 0
                if (getPosition().y < 10.0)
                    getPosition().y = 10.0;
                break;
            case IDLE: // if the racket state is idle, do nothing
                break;
            case GOING_DOWN: // if the racket state is going down
                getPosition().y += racketSpeed * deltaT; // move the racket down

                // Stop the racket from going out of the court if the y position is greater than
                // the court height minus the racket height
                if (getPosition().y + racketSize.y > CourtScene.getCourtSize().y - 10.0)
                    getPosition().y = CourtScene.getCourtSize().y - racketSize.y - 10.0;
                break;
        }
        updateDrawShape(); // update the draw shape of the racket
    }

    // Override the onFree method of the GameObject class to remove the player from
    // the player list
    @Override
    protected void onFree() {
        super.onFree();
        playerList.remove(this);
    }
}
