/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luolastopeli.logiikka.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import luolastopeli.logiikka.Area;

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
            System.out.println("took damage and died");
            return true;
        } else {
            hp -= dmgTaken;
            System.out.println("took damage, but survived");
            return false;
        }
    }

    public boolean attack(Actor target) {
        System.out.println("attacked target: " + target.getType());
        return target.takeDmg(this.dmg);
    }

    public boolean isNextTo(Sprite entity) {
        int entityX = entity.getX();
        int entityY = entity.getY();
        if (entityX == x && (entityY == y - 1 || entityY == y + 1)) return true;
        if (entityY == y && (entityX == x - 1 || entityX == x + 1)) return true;
        return false;
    }
    
    public void interact(Sprite target) {
        String targetType = target.getType();
        if (targetType.equals("ENEMY")) {
            attack((Enemy) target);
            System.out.println("Player attacked enemy!");
        }
    }

}
