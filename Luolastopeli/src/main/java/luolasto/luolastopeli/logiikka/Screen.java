/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package luolasto.luolastopeli.logiikka;

import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

/**
 *
 * @author hexparvi
 */
public class Screen extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root);
        Canvas canvas = new Canvas();
        
        root.getChildren().add(canvas);
        primaryStage.setScene(scene);
        
        primaryStage.show();
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        launch(args);
    }
    
}
