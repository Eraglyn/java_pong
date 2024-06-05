package engine.event;

import javafx.scene.input.KeyCode;

/**
 * A Class for key input events
 *
 * @author See suivi.org file
 * @version 1.0
 */

public class InputEventKey extends InputEvent {
    public enum ActionType {PRESSED, RELEASED, TYPED}

    private final KeyCode eventKey; // the key that the event is triggered by
    private final ActionType eventAction; // whether the key is pressed or released

    // Returns the key that the event is triggered by
    public KeyCode getKey() {
        return eventKey;
    }

    // Returns whether the key is pressed.
    public boolean isPressed() {
        return eventAction == ActionType.PRESSED;
    }

    // Returns whether the key is released.
    public boolean isReleased() {
        return eventAction == ActionType.RELEASED;
    }

    public boolean isTyped() {
        return eventAction == ActionType.TYPED;
    }

    // Constructor used to create a key input event.
    // It normally should not be called directly, but instead by the Engine class.
    public InputEventKey(KeyCode key, ActionType action) {
        eventKey = key;
        eventAction = action;
    }
}
