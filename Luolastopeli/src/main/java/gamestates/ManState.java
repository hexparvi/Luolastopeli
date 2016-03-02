/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestates;

import entities.SpriteEnum;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
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
        vbox.setLayoutX(255);
        vbox.setLayoutY(300);
        vbox.setPrefWidth(125);

        backBtn = new Button("Back to menu");
        backBtn.setMinWidth(vbox.getPrefWidth());
        backBtn.setScaleX(2);
        backBtn.setScaleY(2);

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
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        
        Font font = Font.font("Verdana", FontWeight.LIGHT, 30);
        gc.setFont(font);
        gc.setFill(Color.WHITESMOKE);
        
        gc.drawImage(SpriteEnum.PLAYER_SPRITE.getImage(), 180, 40);
        gc.drawImage(SpriteEnum.ENEMY_SPRITE.getImage(), 170, 70 + (40 * 2) - 30);
        gc.drawImage(SpriteEnum.TREASURE_SPRITE.getImage(), 170, 70 + (40 * 3) - 30);
        
        gc.fillText("You are ", 50, 70);
        gc.fillText("Use arrow keys to move and attack.", 50, 70 + 40);
        gc.fillText("Kill all \t to win.", 50, 70 + (40 * 2));
        gc.fillText("Collect \t to restore HP.", 50, 70 + (40 * 3));
        gc.fillText("Press 'R' anytime to restart.", 50, 70 + (40 * 4));
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
