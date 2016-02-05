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
import luolastopeli.logiikka.Player;

/**
 *
 * @author hexparvi
 */
public class Screen extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root);
        Canvas canvas = new Canvas(500, 500);

        root.getChildren().add(canvas);
        primaryStage.setScene(scene);

        GraphicsContext gc = canvas.getGraphicsContext2D();
        Player testplayer = new Player(2, 2, new Image("file:./src/main/resources/images/playerplaceholder.png"));
        File mapFile = new File("./src/main/resources/maps/testroom.txt");
        Scanner areasrc = new Scanner(mapFile);
        Area testmap = new Area(areasrc);

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                String keycode = e.getCode().toString();
                // works, but doesn't account for simultaneous keypresses
                testplayer.move(keycode, 1, testmap);
            }
        });

        //Main game loop
        new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                gc.clearRect(0, 0, 500, 500);
                testmap.draw(gc);
                testplayer.draw(gc);

            }
        }.start();

        primaryStage.show();
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        launch(args);
    }

}
