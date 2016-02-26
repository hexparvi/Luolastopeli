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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import logic.Game;

/**
 *Handles updating and drawing game over-screen.
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
                game.init(); // restart game
            } catch (FileNotFoundException ex) {
                Logger.getLogger(EndState.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void draw() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        Font font = Font.font("Verdana", FontWeight.BOLD, 48);
        gc.setFont(font);
        gc.setTextAlign(TextAlignment.CENTER);

        if (game.getPlayer().getCurrentHP() == 0) {
            gc.setFill(Color.CRIMSON);
            gc.fillText("Game Over!", canvas.getWidth() / 2, canvas.getHeight() / 2);
        } else {
            gc.setFill(Color.CHARTREUSE);
            gc.fillText("You Win!", canvas.getWidth() / 2, canvas.getHeight() / 2);
        }

        font = Font.font("Verdana", FontWeight.BOLD, 24);
        gc.setFont(font);
        gc.fillText("\nPress R to restart.", canvas.getWidth() / 2, canvas.getHeight() / 2);
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
