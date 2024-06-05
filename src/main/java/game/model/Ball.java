package game.model;

import engine.CanvasItem;
import engine.math.Rect2;
import engine.math.Vector2;
import game.scene.CourtScene;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import javafx.animation.FillTransition;
import javafx.scene.paint.Color;

import java.util.Random;
import java.util.ArrayList;

/**
 * The Ball class represents a ball in the game.
 * It is a subclass of CanvasItem, which means it can be added to the canvas of
 * the game.
 *
 * @author See suivi.org file
 * @version 1.0
 */

public class Ball extends CanvasItem {
    private static final Color[] rainbow = {Color.RED, Color.ORANGE, Color.LIME, Color.TURQUOISE, Color.CYAN, Color.BLUE,
            Color.PURPLE};
    private int posRainbow;
    private final FillTransition ft;
    private static final double radius = 10.0; // radius of the ball
    private static double speed = 250.0; // speed of the ball
    private static final double initSpeed = 250.0;

    private static final ArrayList<Ball> ballList = new ArrayList<>(); // list of all balls in the game

    private final Vector2 initPos; // initial position of the ball
    private Vector2 direction = Vector2.ZERO.duplicate(); // move direction of the ball
    private boolean hasBeenHitPlayer;
    private boolean hasBeenHitWall;

    // Constructor of the Ball class, it takes the initial position of the ball as a
    // parameter
    public Ball(Vector2 position) {
        super(); // call the constructor of the parent class

        Circle cir = new Circle(); // create a circle shape object from javafx
        cir.setRadius(getRadius()); // set the radius of the circle
        cir.setFill(Color.RED); // set the fill color of the circle

        ft = new FillTransition();
        ft.setDuration(Duration.millis(250));
        ft.setFromValue(rainbow[posRainbow % rainbow.length]);
        ft.setToValue(rainbow[(posRainbow + 1) % rainbow.length]);
        ft.setOnFinished(e -> {
            posRainbow++;
            ft.setFromValue(rainbow[posRainbow % rainbow.length]);
            ft.setToValue(rainbow[(posRainbow + 1) % rainbow.length]);
            ft.play();
        });
        ft.setShape(cir);
        ft.play();

        setDrawShape(cir); // set the circle as the shape of the ball
        setPosition(position); // set the position of the ball
        initPos = position.duplicate(); // set the initial position of the ball

        ballList.add(this); // add the ball to the list of balls
        randomizeDirection(); // set a random direction for the ball
    }

    public static ArrayList<Ball> getBallList() {
        return ballList;
    }

    // Returns the radius of the ball
    public static double getRadius() {
        return radius;
    }

    public void setSpeed(double spd) {
        speed = spd;
    }

    public void resetSpeed() {
        speed = initSpeed;
    }

    // Returns the move direction of the ball
    public Vector2 getDirection() {
        return direction;
    }

    // Sets the move direction of the ball
    public void setDirection(Vector2 direction) {
        this.direction = direction.normalized();
    }

    // Randomly set the move direction of the ball
    public void randomizeDirection() {
        Random rd = new Random();
        int x = rd.nextInt(0, 2);
        if (x == 0)
            x = -1;
        direction = new Vector2(x, rd.nextDouble(-0.25, 0.25)).normalized();
    }

    // Returns the Rect2 object that represents the bounding box of the ball
    public Rect2 getBounds() {
        return new Rect2(getPosition().x - radius, getPosition().y - radius, 2 * radius, 2 * radius);
    }

    // Override the update method of the parent class to update the ball
    @Override
    protected void onUpdate(double deltaT) {
        super.onUpdate(deltaT); // call the update method of the parent class
        Vector2 pos = getPosition(); // get the current position of the ball
        // Check if the ball is colliding with one of the players and return the player
        // id if it is
        int collisionPlayer = Player.checkCollision(getBounds());

        if (collisionPlayer != -1 && !hasBeenHitPlayer) {
            // reverse the x direction of the ball if it collides with a player
            // verifier le côté de la raquette qui a taper la balle
            if (getDirection().y > 0.5) {
                setDirection(new Vector2(-getDirection().x,
                        Player.getPlayerById(collisionPlayer).getRacketRect().sideIntersects2(getBounds())
                                * getDirection().y));
                // System.out.println(Player.getPlayerById(collisionPlayer).getRacketRect().sideIntersects2(getBounds()));
            } else if (getDirection().y < -0.5) {
                setDirection(new Vector2(-getDirection().x,
                        -Player.getPlayerById(collisionPlayer).getRacketRect().sideIntersects2(getBounds())
                                * getDirection().y));
                // System.out.println(-Player.getPlayerById(collisionPlayer).getRacketRect().sideIntersects2(getBounds()));
            } else if (getDirection().y > 0) {
                setDirection(new Vector2(-getDirection().x,
                        Player.getPlayerById(collisionPlayer).getRacketRect().sideIntersects(getBounds())
                                + getDirection().y));
                // System.out.println(Player.getPlayerById(collisionPlayer).getRacketRect().sideIntersects(getBounds()));
            } else {
                setDirection(new Vector2(-getDirection().x,
                        Player.getPlayerById(collisionPlayer).getRacketRect().sideIntersects(getBounds())
                                + Math.abs(getDirection().y)));
                // System.out.println(-Player.getPlayerById(collisionPlayer).getRacketRect().sideIntersects(getBounds()));
            }
            hasBeenHitPlayer = true;
            setSpeed(speed + Player.getPlayerById(collisionPlayer).getStrength());
        } else if (collideWall() && !hasBeenHitWall) {
            // reverse the y direction of the ball if it collides with the top or bottom of
            // the court
            hasBeenHitWall = true;
            setDirection(new Vector2(getDirection().x, -getDirection().y));
        }
        if (!CourtScene.getCourtRect().contains(pos)) {

            if (getDirection().x < 0) {
                Player.getPlayerById(1).updateScore();
            } else {
                Player.getPlayerById(0).updateScore();
            }
            // if the ball is out of the court, reset the ball to the initial position
            setPosition(initPos);
            randomizeDirection(); // set a random direction for the ball
            resetSpeed();
        }

        // move the ball by the move direction and the speed,
        // deltaT is the time (in seconds) since the last frame to make the movement
        // frame rate independent
        if (collisionPlayer == -1) // patch du bug causant trop de collision
            hasBeenHitPlayer = false;
        if (!collideWall())
            hasBeenHitWall = false;
        setPosition(getPosition().plus(direction.multiply(speed * deltaT)));
    }

    public boolean collideWall() {
        Vector2 pos = getPosition(); // get the current position of the ball
        return pos.y < radius + 10 || pos.y > CourtScene.getCourtSize().y - radius - 10;
    }

    // Override the destroy method of the parent class to remove the ball from the
    // list of balls
    @Override
    protected void onFree() {
        super.onFree(); // call the destroy method of the parent class
        ballList.remove(this);
    }
}
