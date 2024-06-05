package engine.math;

/**
 * Vector used for 2D math using floating point coordinates.
 * 2-element structure that can be used to represent positions in 2D space or any other pair of numeric values.
 * @author See suivi.org file
 * @version 1.0
 */

public class Vector2 {
    public static final Vector2 ZERO = new Vector2(0, 0); // Zero vector, a vector with all components set to 0.
    public static final Vector2 ONE = new Vector2(1, 1); // One vector, a vector with all components set to 1.
    public static final Vector2 LEFT = new Vector2(-1, 0); // Left vector, a vector with the x component set to -1 and
                                                           // the y component set to 0.
    public static final Vector2 RIGHT = new Vector2(1, 0); // Right vector, a vector with the x component set to 1 and
                                                           // the y component set to 0.
    public static final Vector2 UP = new Vector2(0, -1); // Up vector, a vector with the x component set to 0 and the y
                                                         // component set to -1.
    public static final Vector2 DOWN = new Vector2(0, 1); // Down vector, a vector with the x component set to 0 and the
                                                          // y component set to 1.

    public double x; // The x component of the vector.
    public double y; // The y component of the vector.

    // Constructs a new vector with the x and y components set to 0.
    public Vector2() {
        this.x = 0.0f;
        this.y = 0.0f;
    }

    // Constructs a new vector with the x and y components set to the specified
    // values.
    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Returns true if the vectors are exactly equal.
    public boolean equals(Vector2 other) {
        return (this.x == other.x && this.y == other.y);
    }

    // Returns the distance between this vector and other
    public double distanceTo(Vector2 other) {
        double v0 = other.x - this.x;
        double v1 = other.y - this.y;
        return Math.sqrt(v0 * v0 + v1 * v1);
    }

    // Returns the angle to the given vector, in radians.
    public double angleTo(Vector2 other) {
        return (other.minus(this)).angle();
    }

    // Returns the vector rotated by angle (in radians).
    public Vector2 rotated(double radians) {
        double sine = Math.sin(radians);
        double cosi = Math.cos(radians);
        return new Vector2(this.x * cosi - this.y * sine, this.x * sine + this.y * cosi);
    }

    // Duplicate the vector and return the new vector.
    public Vector2 duplicate() {
        return new Vector2(this.x, this.y);
    }

    // Normalize the vector and return as a new vector.
    public Vector2 normalized() {
        Vector2 vec = duplicate();
        vec.normalize();
        return vec;
    }

    // Flips the vector and returns the new vector.
    public Vector2 flipped() {
        return new Vector2(-this.x, -this.y);
    }

    // Returns the angle of the vector in radians.
    public double angle() {
        return Math.atan2(y, x);
    }

    // Returns the length of the vector.
    public double length() {
        return Math.sqrt(x * x + y * y);
    }

    // Normalizes the vector.
    public void normalize() {
        double l = this.x * this.x + this.y * this.y;
        if (l != 0) {
            l = Math.sqrt(l);
            this.x /= l;
            this.y /= l;
        }
    }

    // Reflects the vector from a plane defined by the given normal.
    public Vector2 reflect(Vector2 normal) {
        return normal.multiply(2.0f).multiply(this.dot(normal)).minus(this);
    }

    // Bounces the vector from a plane defined by the given normal.
    public Vector2 bounce(Vector2 normal) {
        return reflect(normal).flipped();
    }

    // Returns the dot product of this vector and other.
    public double dot(Vector2 other) {
        return this.x * other.x + this.y * other.y;
    }

    // Adds each component of the Vector2 by the components of the given Vector2.
    public Vector2 plus(Vector2 other) {
        return new Vector2(this.x + other.x, this.y + other.y);
    }

    // Subtracts each component of the Vector2 by the components of the given
    // Vector2.
    public Vector2 minus(Vector2 other) {
        return new Vector2(this.x - other.x, this.y - other.y);
    }

    // Multiplies each component of the Vector2 by the components of the given
    // Vector2.
    public Vector2 multiply(Vector2 other) {
        return new Vector2(this.x * other.x, this.y * other.y);
    }

    // Divides each component of the Vector2 by the components of the given Vector2.
    public Vector2 divide(Vector2 other) {
        return new Vector2(this.x / other.x, this.y / other.y);
    }

    // Multiplies each component of the Vector2 by the given value.
    public Vector2 multiply(double other) {
        return new Vector2(this.x * other, this.y * other);
    }

    // Divides each component of the Vector2 by the given value.
    public Vector2 divide(double other) {
        return new Vector2(this.x / other, this.y / other);
    }

    // Returns a string representation of the vector.
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
