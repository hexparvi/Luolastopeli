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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hexparvi
 */
public class Screen extends Application {

    private String input;
    private boolean playerMoved = false;

    @Override
    public void start(Stage primaryStage) throws Exception {
//        Group root = new Group();
//
//        Scene gameScene = new Scene(root);
////        Scene endScene = new Scene(root);
//
//        Canvas gameCanvas = new Canvas(500, 500);
//        Canvas hudCanvas = new Canvas(500, 500);
////        Canvas endCanvas = new Canvas(500, 500);
//
//        root.getChildren().add(gameCanvas);
//        root.getChildren().add(hudCanvas);
////        root.getChildren().add(endCanvas);
//
//        GraphicsContext gameGC = gameCanvas.getGraphicsContext2D();
//        GraphicsContext hudGC = hudCanvas.getGraphicsContext2D();
////        GraphicsContext endGC = endCanvas.getGraphicsContext2D();
//
//        primaryStage.setScene(gameScene);
//
//        gameGC.setFont(Font.getDefault());

        Game game = new Game();
        game.init();
        primaryStage.setScene(game.getScene());

//        gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent e) {
//                input = e.getCode().toString();
//                playerMoved = true;
//            }
//        });


        new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                
                game.updateState();
                game.drawState();
//                game.drawGame(gameGC);
//                game.drawHUD(hudGC);
//                
//                if (game.isOver()) {
////                    primaryStage.setScene(endScene);
////                    game.drawGameOver(endGC);
//                } else if (playerMoved) {
//                    game.update(input);
//                    playerMoved = false;
//                }
            }
        }.start();   

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
