/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package luolastopeli.logiikka;

import javafx.scene.image.Image;

/**
 *
 * @author hexparvi
 */
public class Enemy extends Actor {
    // how to multiple enemy types?
    public Enemy(int initialX, int initialY, Image img) {
        super(initialX, initialY, img);
    }
    
    public void moveTowardPlayer() {
        int targetX = area.getPlayer().getX();
        int targetY = area.getPlayer().getY();
        
        if (targetX > this.x) {
            this.move("RIGHT", 1, area);
            return;
            
        } else if (targetX < this.x) {
            this.move("LEFT", 1, area);
            return;
            
        } else if (targetY > this.y) {
            this.move("DOWN", 1, area);
            return;
            
        } else if (targetY < this.y) {
            this.move("UP", 1, area);
            //cleaner way???
        }
    }
    
    
}
