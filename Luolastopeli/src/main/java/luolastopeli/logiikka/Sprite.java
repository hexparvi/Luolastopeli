/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luolastopeli.logiikka;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author hexparvi
 */
abstract class Sprite {
    int x;
    int y;
    Image img;
    Area area;

    protected Sprite(int initialX, int initialY, Image image) {
        img = image;
        x = initialX;
        y = initialY;
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(img, x * 32, y * 32);
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
}
