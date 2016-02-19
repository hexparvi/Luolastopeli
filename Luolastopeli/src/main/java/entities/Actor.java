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
abstract public class Actor extends Sprite {

    private int speed;// number of steps taken in one turn
    private int hp;
    private int dmg;

    protected Actor(int initialX, int initialY, String imgPath) {
        super(initialX, initialY, imgPath);
    }

    public int getHP() {
        return hp;
    }
    
    public void setHP(int newHp) {
        hp = newHp;
    }
    
    public void setDmg(int newDmg) {
        dmg = newDmg;
    }

    public int getDmg() {
        return dmg;
    }

    public boolean takeDmg(int dmgTaken) {
        if (dmgTaken > hp) {
            hp = 0;
            return true;
        } else {
            hp -= dmgTaken;
            return false;
        }
    }

    public boolean attack(Actor target) {
        return target.takeDmg(this.dmg);
    }

    public boolean isNextTo(Sprite entity) {
        int entityX = entity.getX();
        int entityY = entity.getY();
        if (entityX == x && (entityY == y - 1 || entityY == y + 1)) return true;
        if (entityY == y && (entityX == x - 1 || entityX == x + 1)) return true;
        return false;
    }

}
