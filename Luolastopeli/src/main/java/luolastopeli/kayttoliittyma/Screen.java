/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luolastopeli.kayttoliittyma;

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
import javafx.stage.Stage;
import luolastopeli.logiikka.Area;
import luolastopeli.logiikka.Game;
import luolastopeli.logiikka.entities.EntityManager;
import luolastopeli.logiikka.entities.Player;

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
        Canvas canvas = new Canvas(500, 500);

        root.getChildren().add(canvas);
        primaryStage.setScene(scene);
        GraphicsContext gc = canvas.getGraphicsContext2D();

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
                game.draw(gc);
                if (playerMoved) {
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