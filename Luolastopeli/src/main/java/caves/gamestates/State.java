/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caves.gamestates;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import caves.logic.Game;

/**
 *Provides methods for updating and drawing game states.
 * @author hexparvi
 */
public abstract class State {

    Game game;
    Canvas canvas;
    Group group;
    GraphicsContext gc;

    /**
     * Sets up the JavaFX elements needed for drawing any State.
     * @param game the Game that uses this state
     */
    public State(Game game) {
        this.game = game;
        this.group = new Group();
        this.canvas = new Canvas(640, 640);
        gc = canvas.getGraphicsContext2D();
        group.getChildren().add(canvas);
    }

    public abstract void update();

    public abstract void draw();
    
    public abstract Group getGroup();
    
    public abstract void setHandlers(Scene scene);
    
    public abstract void removeHandlers(Scene scene);
}
