/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caves.entities;

/**
 *Stores points and healing gained from collecting treasure.
 * @author hexparvi
 */
public class Treasure extends Sprite {
    private int worth;
    private int healing;

    /**
     * Sets Treasure type, worth and healing amount.
     * @param x x-coordinate of Treasure
     * @param y y-coordinate of Treasure
     */
    public Treasure(int x, int y) {
        super(x, y, SpriteEnum.TREASURE_SPRITE.getPath());
        super.setType("TREASURE");
        this.worth = 5;
        this.healing = 2;
    }
    
    public int getWorth() {
        return worth;
    }
    
    public int getHealing() {
        return healing;
    }
}
