/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package caves.entities;

/**
 *Handles player healing and point collection.
 * @author hexparvi
 */
public class Player extends Actor {
    private int points;

    /**
     * Sets Player HP, dmg, type and points.
     * @param x x-coordinate of Player
     * @param y y-coordinate of Player
     */
    public Player(int x, int y) {
        super(x, y, SpriteEnum.PLAYER_SPRITE.getPath());
        super.setMaxHP(10);
        super.setCurrentHP(10);
        super.setDmg(5);
        super.setType("PLAYER");
        points = 0;
    }
    
    public int getPoints() {
        return points;
    }
    
    /**
     * Increases player currentHP by amount, capped at maxHP.
     * @param amount amount of HP to be healed
     */
    public void heal(int amount) {
        if (amount < 0) {
            return;
        }
        if (getCurrentHP() + amount <= getMaxHP()) {
            setCurrentHP(getCurrentHP() + amount);
        } else {
            setCurrentHP(getMaxHP());
        }
    }
    
    /**
     * Increases player points.
     * @param amount amount of points to add
     */
    public void increasePoints(int amount) {
        points += amount;
    }
    
    public void setPoints(int points) {
        this.points = points;
    }
}
