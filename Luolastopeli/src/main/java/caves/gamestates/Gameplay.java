/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caves.gamestates;

import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import caves.logic.Game;

/**
 * Handles updating and drawing gameplay.
 *
 * @author hexparvi
 */
public class Gameplay extends State {

    private Canvas hudCanvas;
    private GraphicsContext hudGC;
    private String input;
    private boolean playerMoved;
    private EventHandler<KeyEvent> moveHandler;
    private EventHandler<KeyEvent> restartHandler;
    private ArrayList<String> legalMoves;

    public Gameplay(Game game) {
        super(game);
        hudCanvas = new Canvas(canvas.getWidth(), canvas.getHeight());
        hudGC = hudCanvas.getGraphicsContext2D();

        playerMoved = false;
        input = "";
        
        legalMoves = new ArrayList<>();
        legalMoves.add("UP");
        legalMoves.add("DOWN");
        legalMoves.add("LEFT");
        legalMoves.add("RIGHT");
        
        restartHandler = (KeyEvent e) -> {
            if (e.getCode() == KeyCode.R) {
                game.restart();
            }
        };
        
        moveHandler = (KeyEvent e) -> {
            input = e.getCode().toString();
            if (legalMoves.contains(input)) {
                playerMoved = true;
            }
        };
        
        group.getChildren().add(hudCanvas);
    }

    @Override
    public void setHandlers(Scene scene) {
        scene.setOnKeyReleased(moveHandler);
        scene.setOnKeyPressed(restartHandler);
    }

    @Override
    public void update() {
        if (playerMoved) {
            game.getManager().updatePlayer(input);
            game.getManager().updateEnemies();
            playerMoved = false;
        }
        if (game.getPlayer().getCurrentHP() == 0 || game.getArea().getEnemies().isEmpty()) {
            game.setState(game.getState("END"));
        }
    }

    @Override
    public void draw() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        game.getArea().draw(gc);
        hudGC.clearRect(0, 0, hudCanvas.getWidth(), hudCanvas.getHeight());
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
