/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import javafx.scene.image.Image;
import logic.Area;

/**
 *
 * @author hexparvi
 */
public class Enemy extends Actor {
    public Enemy(int initialX, int initialY, String imgPath) {
        super(initialX, initialY, imgPath);
        super.setHP(5);
        super.setDmg(1);
        super.setType("ENEMY");
    }
    
    @Override
    public void act() {
    }
    
    /**
     * Selects direction based on target coordinates.
     * 
     * @param playerX x-coordinate of target player
     * @param playerY y-coordinate of target player
     * @return direction chosen as String
     */
    public String findDirection(int playerX, int playerY) {
        if (x > playerX) {
            return "LEFT";
        } else if (x < playerX) {
            return "RIGHT";
        } else if (y > playerY) {
            return "UP";
        } else if (y < playerY) {
            return "DOWN";
        }
        return "";
    }
}
