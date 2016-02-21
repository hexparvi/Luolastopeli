/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestates;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import logic.Game;

/**
 *
 * @author hexparvi
 */
public class PlayState extends State {

    private Canvas hudCanvas;
    private GraphicsContext hudGC;
    private String input;
    private boolean playerMoved;
    private EventHandler<KeyEvent> moveHandler;

    public PlayState(Game game) {
        super(game);
        hudCanvas = new Canvas(500, 500);
        hudGC = hudCanvas.getGraphicsContext2D();
        group.getChildren().add(hudCanvas);
        playerMoved = false;
        input = "";
        moveHandler = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                input = e.getCode().toString();
                playerMoved = true;
            }
        };
    }

    @Override
    public void setHandlers(Scene scene) {
        scene.setOnKeyReleased(moveHandler);
    }

    @Override
    public void update() {
        if (playerMoved) {
            game.getManager().updatePlayer(input);
            game.getManager().updateEnemies();
            playerMoved = false;
        }
        if (game.getPlayer().getCurrentHP() == 0) {
            game.setState(game.getStates().get("END"));
        }
        if (game.getArea().getEnemies().isEmpty()) {
            game.setState(game.getStates().get("END"));

        }
    }

    @Override
    public void draw() {
        gc.clearRect(0, 0, 500, 500);
        game.getArea().draw(gc);
        hudGC.clearRect(0, 0, 500, 500);
        game.getDisplay().draw(hudGC);
    }

    @Override
    public Group getGroup() {
        return group;
    }

    @Override
    public void removeHandlers(Scene scene) {
        scene.setOnKeyPressed(null);
    }

}
