/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import logic.Game;

/**
 *
 * @author hexparvi
 */
public class Screen extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Game game = new Game();
        game.init();
        primaryStage.setScene(game.getScene());

        new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                game.updateState();
                game.drawState();
            }
        }.start();   

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
