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
abstract class Actor extends Sprite {
    //needs update() method? to encapsulate
    private int speed;// number of steps taken in one turn
    private int hp;
    private int dmg;

    protected Actor(int initialX, int initialY, Image img) {
        super(initialX, initialY, img);
    }

    public void move(String direction, int steps, Area map) {
        // recursive check for multiple steps?
        switch (direction) {
            case "UP":
                if (map.isWalkable(x, y - steps)) {
                    y -= steps;
                }
                break;
            case "DOWN":
                if (map.isWalkable(x, y + steps)) {
                    y += steps;
                }
                break;
            case "LEFT":
                if (map.isWalkable(x - steps, y)) {
                    x -= steps;
                }
                break;
            case "RIGHT":
                if (map.isWalkable(x + steps, y)) {
                    x += steps;
                }
                break;
        }
    }

    public int getHP() {
        return hp;
    }

    public int getDmg() {
        return dmg;
    }

    public void takeDmg(int dmgTaken) {
        if (dmgTaken > hp) {
            hp = 0;
        } else {
            hp -= dmgTaken;
        }
    }
    
    public void attack(Actor target) {// where to check when to attack? main loop?
        target.takeDmg(this.dmg);
    }

}
