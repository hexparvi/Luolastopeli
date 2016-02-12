/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package luolastopeli.logiikka.entities;

import javafx.scene.image.Image;
import luolastopeli.logiikka.Area;

/**
 *
 * @author hexparvi
 */
public class Enemy extends Actor {
    // how to multiple enemy types?
    public Enemy(int initialX, int initialY, String imgPath) {
        super(initialX, initialY, imgPath);
    }
    
    public void moveTowardPlayer(Player player, Area map) {
        //System.out.println("enemy moved");
        int targetX = player.getX();
        int targetY = player.getY();
        if (targetX > x) {
            move("RIGHT", 1, map);
            
        } else if (targetX < x) {
            move("LEFT", 1, map);
            
        } else if (targetY > y) {
            move("DOWN", 1, map);
            
        } else if (targetY < y) {
            move("UP", 1, map);
        }
        if (x == targetX && y == targetY) {
            attack(player);
        }
    }
    
    
}
