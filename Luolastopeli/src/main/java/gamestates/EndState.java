/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gamestates;

import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import logic.Game;

/**
 *
 * @author hexparvi
 */
public class EndState extends State {
    
    private EventHandler<KeyEvent> restartHandler;
    private KeyCode input;
    
    public EndState(Game game) {
        super(game);
        restartHandler = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                input = e.getCode();
            }
        };
    }

    @Override
    public void update() {
        if (input == KeyCode.R) {
            input = null;
            try {
                game.init(); // restarts game
            } catch (FileNotFoundException ex) {
                Logger.getLogger(EndState.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void draw() {
        gc.clearRect(0, 0, 500, 500);
        gc.setFill(Color.RED);
        gc.fillText("Game Over!", 250, 250);
    }

    @Override
    public Group getGroup() {
        return group;
    }

    @Override
    public void setHandlers(Scene scene) {
        scene.setOnKeyReleased(restartHandler);
    }

    @Override
    public void removeHandlers(Scene scene) {
        scene.setOnKeyReleased(null);
    }
    
}
