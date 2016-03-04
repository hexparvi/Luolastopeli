/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caves.gamestates;

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
import caves.logic.Game;

/**
 * Handles updating and drawing game over-screen.
 *
 * @author hexparvi
 */
public class GameOver extends State {

    private EventHandler<KeyEvent> restartKeyHandler;
    private EventHandler<ActionEvent> restartHandler;
    private EventHandler<ActionEvent> menuHandler;
    private KeyCode input;
    private Button restartBtn;
    private Button menuBtn;
    private VBox vbox;

    /**
     * Sets up the JavaFX elements necessary for drawing this state.
     * @param game the Game that uses this state
     */
    public GameOver(Game game) {
        super(game);
        vbox = new VBox(35);
        vbox.setPrefWidth(130);
        vbox.setLayoutX(255);
        vbox.setLayoutY(400);
        
        restartBtn = new Button("Restart");
        restartBtn.setMinWidth(vbox.getPrefWidth());
        restartBtn.setScaleX(2);
        restartBtn.setScaleY(2);
        
        menuBtn = new Button("Back to menu");
        menuBtn.setMinWidth(vbox.getPrefWidth());
        menuBtn.setScaleX(2);
        menuBtn.setScaleY(2);
        
        restartKeyHandler = (KeyEvent e) -> {
            if (e.getCode() == KeyCode.R) {
                game.restart();
            }
        };
        
        restartHandler = (ActionEvent e) -> {
            game.restart();
        };
        
        menuHandler = (ActionEvent e) -> {
            game.init();
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
