package engine;

import engine.event.InputEvent;

/**
 * The base class for all game objects.
 *
 * @author See suivi.org file
 * @version 1.0
 */

public class GameObject {
    private GameScene parentScene; // the parent scene of the object
    private boolean queuedToFree = false; // whether the object is queued to be freed
    private boolean canPause = true; // whether the object can be paused

    void setParentScene(GameScene parentScene) {
        this.parentScene = parentScene;
    }

    protected void onEnterScene(GameScene scene) {
        // Called when the object is ready to be used
    }

    // An overrideable function that is called every frame to update the object
    protected void onUpdate(double deltaT) {
    }

    // An overrideable function that is called when there is an input event
    protected void onInput(InputEvent event) {
    }

    protected void onExitScene(GameScene scene) {
        // Called when the object is freed
    }

    // An overrideable function that is called when the object is removed from the
    // Engine
    protected void onFree() {
    }

    // Mark the object to be freed
    public void queueFree() {
        queuedToFree = true;
    }

    public void setCanPause(boolean canPause) {
        this.canPause = canPause;
    }

    // Returns whether the object is queued to be freed
    public boolean isQueuedToFree() {
        return queuedToFree;
    }

    // Returns whether the object can be paused
    public boolean canPause() {
        return canPause;
    }

    public GameScene getParentScene() {
        return parentScene;
    }
}
