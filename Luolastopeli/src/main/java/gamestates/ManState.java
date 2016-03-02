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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logic.Game;

/**
 *
 * @author hexparvi
 */
public class ManState extends State {

    private VBox vbox;
    private Button backBtn;
    private EventHandler<ActionEvent> backHandler;

    public ManState(Game game) {
        super(game);
        vbox = new VBox();
        backBtn = new Button("Back to menu");
        backHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                game.setState(game.getState("MENU"));
            }
        };
        backBtn.setOnAction(backHandler);
        vbox.getChildren().add(backBtn);
        group.getChildren().add(vbox);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw() {
        Font font = Font.font("Verdana", FontWeight.MEDIUM, 24);
        gc.setFont(font);
        gc.setFill(Color.BLACK);
        gc.fillText("Move, Attack\n"
                + "Kill all\t to win.\n"
                + "Collect\t to restore HP.\n"
                + "Press 'R' anytime to restart.", 50, 50);
    }

    @Override
    public Group getGroup() {
        return group;
    }

    @Override
    public void setHandlers(Scene scene) {
    }

    @Override
    public void removeHandlers(Scene scene) {
    }

}
