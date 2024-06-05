package engine;

import engine.event.InputEvent;
import engine.event.InputEventKey;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class GameScene {
    private final ArrayList<GameObject> childList = new ArrayList<>(); // list of game objects in the scene
    private final Pane canvas; // the canvas of the scene
    private final Scene scene;

    public Pane getCanvas() {
        return canvas;
    }

    public Scene getScene() {
        return scene;
    }

    public void addChild(GameObject child) {
        childList.add(child);
        child.setParentScene(this);
        child.onEnterScene(this);
    }

    public void removeChild(GameObject child) {
        childList.remove(child);
        child.setParentScene(null);
        child.onExitScene(this);
    }

    protected void free() {
        for (GameObject child : childList) {
            child.setParentScene(null);
            child.onExitScene(this);
            child.onFree();
        }
        childList.clear();
    }

    // Process the key input event and pass it to the game objects
    protected void onInput(InputEvent event) {

        // Loop through all game objects and pass the event to them
        for (int i = childList.size() - 1; i >= 0; i--) {
            GameObject obj = childList.get(i);

            //if the object is not paused, then process the event
            if (obj.canPause() && Engine.getSingleton().isPaused()) {
                continue;
            }

            obj.onInput(event); // pass the event to the game object by calling its onInput method
        }
    }

    // Called every frame to update the game objects
    protected void onUpdate(double delta) {
        // Loop through all game objects and update them
        for (int i = childList.size() - 1; i >= 0; i--) {
            GameObject obj = childList.get(i);

            // If the object is marked for deletion, remove it from the list
            if (obj.isQueuedToFree()) {
                removeChild(obj);
                obj.onFree(); // call the onFree method of the object
                continue;
            }

            //if the object is not paused, then process the event
            if (obj.canPause() && Engine.getSingleton().isPaused()) {
                continue;
            }

            obj.onUpdate(delta); // call the onUpdate method of the object
        }
    }

    public void remapInputEvent() {
        this.scene.setOnKeyPressed(ev -> {
            InputEventKey event = new InputEventKey(ev.getCode(), InputEventKey.ActionType.PRESSED);
            this.onInput(event);
        });
        // Set the event handlers for the key input events(when a key is released)
        this.scene.setOnKeyReleased(ev -> {
            InputEventKey event = new InputEventKey(ev.getCode(), InputEventKey.ActionType.RELEASED);
            this.onInput(event);
        });
        this.scene.setOnKeyTyped(ev -> {
            InputEventKey event = new InputEventKey(ev.getCode(), InputEventKey.ActionType.TYPED);
            this.onInput(event);
        });
    }

    public GameScene(Pane canvas) {
        super();
        this.canvas = canvas;
        this.scene = new Scene(this.canvas);
        remapInputEvent();
    }
}
