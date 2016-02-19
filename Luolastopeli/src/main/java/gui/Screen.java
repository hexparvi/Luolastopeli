/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import logic.Area;
import logic.Game;
import entities.EntityManager;
import entities.Player;

/**
 *
 * @author hexparvi
 */
public class Screen extends Application {

    private String input;
    private boolean playerMoved = false;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root);
        Canvas gameCanvas = new Canvas(500, 500);
        Canvas hudCanvas = new Canvas(500, 500);

        root.getChildren().add(gameCanvas);
        root.getChildren().add(hudCanvas);
        primaryStage.setScene(scene);
        GraphicsContext gameGC = gameCanvas.getGraphicsContext2D();
        GraphicsContext hudGC = hudCanvas.getGraphicsContext2D();
        
        gameGC.setFont(Font.getDefault());

        Game game = new Game();
        game.init();

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                input = e.getCode().toString();
                playerMoved = true;
            }
        });

        new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                game.drawGame(gameGC);
                game.drawHUD(hudGC);
                if (game.isOver()) {
                    game.gameOver();
                } else if (playerMoved) {
                    game.update(input);
                    playerMoved = false;
                }
            }
        }.start();

        primaryStage.show();
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        launch(args);
    }

}
