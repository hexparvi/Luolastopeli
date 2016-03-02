/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestates;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import logic.Game;

/**
 *
 * @author hexparvi
 */
public class MenuState extends State {

    private VBox vbox;
    private EventHandler<ActionEvent> startHandler;
    private EventHandler<ActionEvent> manHandler;
    private Button manBtn;
    private Button startBtn;

    public MenuState(Game game) {
        super(game);
        vbox = new VBox(5);
        vbox.setLayoutX(255);
        vbox.setLayoutY(350);
        manBtn = new Button("How to Play");
        startBtn = new Button("Start Game");
        manBtn.setPrefSize(200, 50);
        startBtn.setScaleX(2);
        startBtn.setScaleY(2);
        startHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                game.setState(game.getState("PLAY"));
            }
        };
        manHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                game.setState(game.getState("MANUAL"));
            }
        };
        vbox.getChildren().addAll(manBtn, startBtn);
        group.getChildren().add(vbox);
    }

    @Override
    public void update() {
        
    }

    @Override
    public void draw() {
        
    }

    @Override
    public Group getGroup() {
        return group;
    }

    @Override
    public void setHandlers(Scene scene) {
        manBtn.setOnAction(manHandler);
        startBtn.setOnAction(startHandler);
    }

    @Override
    public void removeHandlers(Scene scene) {
    }

}
