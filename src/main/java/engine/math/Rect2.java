package engine.math;

/**
 * 2D axis-aligned bounding box using floating point coordinates.
 * Rect2 consists of a position, a size, and several utility functions. It is
 * typically used for fast overlap tests.
 *
 * @author See suivi.org file
 * @version 1.0
 */

public class Rect2 {
    @SuppressWarnings("CanBeFinal")
    public Vector2 position; // The position of the Rect2.
    @SuppressWarnings("CanBeFinal")
    public Vector2 size; // The size of the Rect2.

    // Constructs a new Rect2 with the position and size set to 0.
    public Rect2() {
        this.position = new Vector2();
        this.size = new Vector2();
    }

    // Constructs a new Rect2 with the position and size set to the specified
    // Vector2.
    public Rect2(Vector2 position, Vector2 size) {
        this.position = position.duplicate();
        this.size = size.duplicate();
    }

    // Constructs a new Rect2 with the position and size set to the specified
    // values.
    public Rect2(double x, double y, double width, double height) {
        this.position = new Vector2(x, y);
        this.size = new Vector2(width, height);
    }

    // Returns true if the Rect2 contains the given point.
    public boolean contains(Vector2 point) {
        return (point.x >= position.x && point.x <= position.x + size.x &&
                point.y >= position.y && point.y <= position.y + size.y);
    }

    // Returns true if the Rect2 overlaps with others.
    public boolean intersects(Rect2 other) {
        return (position.x < other.position.x + other.size.x &&
                position.x + size.x > other.position.x &&
                position.y < other.position.y + other.size.y &&
                position.y + size.y > other.position.y);
    }

    public double sideIntersects(Rect2 other) {
        // si la balle est au dessus du milieu de la raquette
        return -((((position.y + position.y + size.y) / 2)
                - ((other.position.y + other.position.y + other.size.y) / 2)) / 100);
    }

    public double sideIntersects2(Rect2 other) {
        if ((other.position.y + (other.position.y + other.size.y)) / 2 < (position.y + (position.y + size.y)) / 2)
            return -0.75;
        else
            return 0.75;
    }

    // Duplicate the Rect2 and return the new Rect2.
    public Rect2 duplicate() {
        return new Rect2(position.duplicate(), size.duplicate());
    }

    // Returns a string representation of the Rect2.
    public String toString() {
        return "Rect2(" + position.x + ", " + position.y + ", " + size.x + ", " + size.y + ")";
    }
}
