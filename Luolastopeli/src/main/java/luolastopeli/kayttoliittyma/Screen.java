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
import luolastopeli.logiikka.entities.EntityManager;
import luolastopeli.logiikka.entities.Player;

/**
 *
 * @author hexparvi
 */
public class Screen extends Application {

    public boolean playerTurn;
    public boolean playerMoved;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root);
        Canvas canvas = new Canvas(500, 500);

        root.getChildren().add(canvas);
        primaryStage.setScene(scene);

        GraphicsContext gc = canvas.getGraphicsContext2D();
        File mapFile = new File("./src/main/resources/maps/testroom.txt");
        Scanner areasrc = new Scanner(mapFile);
        Area testmap = new Area(areasrc);
        Player testplayer = testmap.getPlayer();
        EntityManager testmanager = new EntityManager(testmap.getEnemies(), testplayer, testmap);
        ArrayList<String> input = new ArrayList<>();
        playerMoved = false;
        
//        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent e) {
//                String keycode = e.getCode().toString();
//                input.add(keycode);
//            }
//        });
        playerTurn = true;
        
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                if (playerTurn) {
                    testplayer.move(e.getCode().toString(), 1, testmap);
                    playerTurn = false;
                }
                
            }
        });

        

        //Main game loop
        new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                gc.clearRect(0, 0, 500, 500);
                testmap.draw(gc);
                if (!playerTurn) {
                    testmanager.updateEntities();
                    playerTurn = true;
                }
                testmanager.drawEntities(gc);
                testmanager.drawEntity(testplayer, gc);
            }
        }.start();

        primaryStage.show();
    }
    
    public void playTurn() {
        
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        launch(args);
    }

}
