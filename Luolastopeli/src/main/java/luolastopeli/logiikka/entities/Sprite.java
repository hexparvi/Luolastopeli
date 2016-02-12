/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luolastopeli.logiikka.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author hexparvi
 */
abstract public class Sprite {
    int x;
    int y;
    String imagePath;
    //Area area;

    protected Sprite(int initialX, int initialY, String imgPath) {
        imagePath = imgPath;
        x = initialX;
        y = initialY;
    }

//    public void draw(GraphicsContext gc) {
//        gc.drawImage(img, x * 32, y * 32);
//    }
    
//    public void setArea(Area where) {
//        area = where;
//    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public String getImagePath() {
        return imagePath;
    }
}
