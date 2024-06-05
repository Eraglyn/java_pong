package game.model;

/**
 * The RacketController interface defines the methods that a racket controller
 * should implement.
 * Game objects that are racket controllers should implement this interface,
 * like PlayerController and AIController.
 *
 * @version 1.0
 */

public interface RacketController {
    enum RacketState {
        GOING_UP, IDLE, GOING_DOWN
    } // The racket state enum to define the racket movement states

    RacketState getRacketState(); // Returns the racket state
}
