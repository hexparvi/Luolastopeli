/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

/**
 *
 * @author hexparvi
 */
public class Player extends Actor {
    private int points;

    public Player(int x, int y, String imgPath) {
        super(x, y, imgPath);
        super.setMaxHP(10);
        super.setCurrentHP(10);
        super.setDmg(5);
        super.setType("PLAYER");
        points = 0;
    }
    
    public int getPoints() {
        return points;
    }
    
    public void increasePoints(int amount) {
        points += amount;
    }
    
    public void setPoints(int points) {
        this.points = points;
    }
}
