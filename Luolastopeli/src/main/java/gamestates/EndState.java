/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestates;

import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import logic.Game;

/**
 * Handles updating and drawing game over-screen.
 *
 * @author hexparvi
 */
public class EndState extends State {

    private EventHandler<KeyEvent> restartKeyHandler;
    private EventHandler<ActionEvent> restartHandler;
    private EventHandler<ActionEvent> menuHandler;
    private KeyCode input;
    private Button restartBtn;
    private Button menuBtn;
    private VBox vbox;

    public EndState(Game game) {
        super(game);
        vbox = new VBox();
        restartBtn = new Button("Restart");
        menuBtn = new Button("Back to menu");
        restartKeyHandler = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                if (e.getCode() == KeyCode.R) {
                    game.restart();
                }
            }
        };
        restartHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                game.restart();
            }  
        };
        menuHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                game.init();
            }
        };
        restartBtn.setOnAction(restartHandler);
        menuBtn.setOnAction(menuHandler);
        vbox.getChildren().addAll(restartBtn, menuBtn);
        group.getChildren().add(vbox);
    }

    @Override
    public void update() {
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
        scene.setOnKeyPressed(restartKeyHandler);
    }

    @Override
    public void removeHandlers(Scene scene) {
        scene.setOnKeyPressed(null);
    }

}
