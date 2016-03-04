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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import caves.logic.Game;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author hexparvi
 */
public class MainMenu extends State {

    private VBox vbox;
    private EventHandler<ActionEvent> startHandler;
    private EventHandler<ActionEvent> manHandler;
    private Button manBtn;
    private Button startBtn;

    public MainMenu(Game game) {
        super(game);
        vbox = new VBox(35);
        vbox.setPrefWidth(100);
        vbox.setLayoutX(270);
        vbox.setLayoutY(350);

        manBtn = new Button("How to Play");
        manBtn.setMinWidth(vbox.getPrefWidth());
        manBtn.setScaleX(2);
        manBtn.setScaleY(2);

        startBtn = new Button("Start Game");
        startBtn.setMinWidth(vbox.getPrefWidth());
        startBtn.setScaleX(2);
        startBtn.setScaleY(2);

        startHandler = (ActionEvent e) -> {
            game.setState(game.getState("PLAY"));
        };

        manHandler = (ActionEvent e) -> {
            game.setState(game.getState("MANUAL"));
        };

        vbox.getChildren().addAll(startBtn, manBtn);
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

        Font font = Font.font("Verdana", FontWeight.BOLD, 64);
        gc.setFont(font);
        gc.setFill(Color.WHITESMOKE);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.fillText("Caves", canvas.getWidth() / 2, canvas.getHeight() / 3);
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
