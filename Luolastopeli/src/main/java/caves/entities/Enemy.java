/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caves.entities;

/**
 *Handles enemy movement behavior.
 * @author hexparvi
 */
public class Enemy extends Actor {

    public Enemy(int x, int y) {
        super(x, y, SpriteEnum.ENEMY_SPRITE.getPath());
        super.setMaxHP(5);
        super.setCurrentHP(5);
        super.setDmg(1);
        super.setType("ENEMY");
    }

    /**
     * Selects direction based on target coordinates.
     *
     * @param playerX x-coordinate of target player
     * @param playerY y-coordinate of target player
     * @return direction chosen as String
     */
    public String findDirection(int playerX, int playerY) {
        if (Math.random() < 0.5) {
            if (x > playerX) {
                return "LEFT";
            } else if (x < playerX) {
                return "RIGHT";
            } else if (y > playerY) {
                return "UP";
            } else if (y < playerY) {
                return "DOWN";
            }
        } else {
            if (y > playerY) {
                return "UP";
            } else if (y < playerY) {
                return "DOWN";
            } else if (x > playerX) {
                return "LEFT";
            } else if (x < playerX) {
                return "RIGHT";
            }
        }
        return "";
    }
}
