/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import logic.Area;

/**
 *Stores sprite position, type and file path to image.
 * @author hexparvi
 */
abstract public class Sprite {
    int x;
    int y;
    String type;
    String imgPath;

    protected Sprite(int x, int y, String imgPath) {
        this.imgPath = imgPath;
        this.x = x;
        this.y = y;
    }
    
    public void act() {
        
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
