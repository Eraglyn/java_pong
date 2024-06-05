package engine;

import engine.math.Vector2;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * Base class for all visual objects in the game
 *
 * @author See suivi.org file
 * @version 1.0
 */

public class CanvasItem extends GameObject {
    private Vector2 position; // position of the object
    private boolean visible; // whether the object is visible
    private Shape drawShape; // the shape of the object

    // Returns the position of the object
    public Vector2 getPosition() {
        return position;
    }

    // Sets the position of the object
    public void setPosition(Vector2 newPos) {
        position = newPos.duplicate();
        updateDrawShape(); // update the shape of the object
    }

    // Set the x position of the object
    public void setPositionX(double x) {
        position.x = x;
        updateDrawShape();
    }

    // Set the y position of the object
    public void setPositionY(double y) {
        position.y = y;
        updateDrawShape();
    }

    // Returns whether the object is visible
    public boolean getVisible() {
        return visible;
    }

    // Sets whether the object is visible
    public void setVisible(boolean enabled) {
        visible = enabled;
        updateDrawShape();
    }

    // Sets the shape of the object
    public void setDrawShape(Shape newShape) {
        if (getParentScene() != null && drawShape != null) {
            getParentScene().getCanvas().getChildren().remove(drawShape); // remove the old shape from the canvas
        }
        drawShape = newShape;
        if (getParentScene() != null && drawShape != null) {
            getParentScene().getCanvas().getChildren().add(drawShape); // add the new shape to the canvas
        }
        updateDrawShape();
    }

    // Updates the shape of the object
    public void updateDrawShape() {
        if (drawShape != null) {
            drawShape.setVisible(visible); // set the visibility of the shape

            // Check if the shape is a rectangle
            if (drawShape instanceof Rectangle) {
                ((Rectangle) drawShape).setX(position.x); // set the x position of the shape
                ((Rectangle) drawShape).setY(position.y); // set the y position of the shape

                // Check if the shape is a circle
            } else if (drawShape instanceof Circle) {
                ((Circle) drawShape).setCenterX(position.x); // set the x position of the shape
                ((Circle) drawShape).setCenterY(position.y); // set the y position of the shape
            }
        }
    }

    @Override
    protected void onEnterScene(GameScene scene) {
        super.onEnterScene(scene);
        if (drawShape != null) {
            scene.getCanvas().getChildren().add(drawShape); // add the new shape to the canvas
        }
    }

    // Override the onUpdate method of the GameObject class, so that the object can
    // be updated
    @Override
    protected void onUpdate(double deltaT) {
        super.onUpdate(deltaT); // call the onUpdate method of the GameObject class
        updateDrawShape(); // update the shape of the object
    }

    @Override
    protected void onExitScene(GameScene scene) {
        super.onExitScene(scene);
        if (drawShape != null) {
            scene.getCanvas().getChildren().remove(drawShape); // remove the old shape from the canvas
        }
    }

    protected void onFree() {
        super.onFree(); // call the onFree method of the GameObject class
        setDrawShape(null); // remove the shape of the object
    }

    // Constructor of the CanvasItem class
    public CanvasItem() {
        super();
        position = Vector2.ZERO.duplicate(); // set the position of the object to (0,0)
        visible = true; // set the visibility of the object to true
        drawShape = null; // set the shape of the object to null
    }
}
