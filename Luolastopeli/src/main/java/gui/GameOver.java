/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author hexparvi
 */
public class GameOver extends Scene{
    public GameOver(Parent parent) {
        super(parent);
    }
    
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.strokeText("Game Over!", 250, 250);
        gc.strokeText("Press 'R' to try again.", 250, 300);
    }
}
