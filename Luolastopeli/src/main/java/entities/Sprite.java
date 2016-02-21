/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

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
    String imgPath;

    protected Sprite(int x, int y, String imgPath) {
        this.imgPath = imgPath;
        this.x = x;
        this.y = y;
        speed = 1;
    }
    
    public void act() {
        
    }
    
    /**
     * Moves sprite in area.
     * @param direction direction of movement
     * @param map area in which sprite is located
     */
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
    
    public void setX(int x) {
        this.x = x;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getType() {
        return type;
    }
    
    public String getImagePath() {
        return imgPath;
    }
}
