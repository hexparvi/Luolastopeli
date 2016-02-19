/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logic.Area;

/**
 *
 * @author hexparvi
 */
abstract public class Sprite {
    int x;
    int y;
    int speed;
    String type;

    protected Sprite(int initialX, int initialY, String imgPath) {
        type = imgPath;
        x = initialX;
        y = initialY;
        speed = 1;
    }
    
    public void act() {
        
    }
    
    public void move(String direction, Area map) {
        map.removeEntityFromPos(x, y);
        switch (direction) {
            case "UP":
                if (map.isWalkable(x, y - speed)) {
                    y = y - speed;
                }
                break;
            case "DOWN":
                if (map.isWalkable(x, y + speed)) {
                    y = y + speed;
                }
                break;
            case "LEFT":
                if (map.isWalkable(x - speed, y)) {
                    x = x - speed;
                }
                break;
            case "RIGHT":
                if (map.isWalkable(x + speed, y)) {
                    x = x + speed;
                }
                break;
        }
        map.setEntityToPos(x, y, this);
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public void setX(int newX) {
        x = newX;
    }
    
    public void setY(int newY) {
        y = newY;
    }
    
    public void setType(String newType) {
        type = newType;
    }
    
    public String getType() {
        return type;
    }
    
    public String getImagePath() {
        return type;
    }
}
