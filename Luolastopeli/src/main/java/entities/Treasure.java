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
public class Treasure extends Sprite {
    private int worth;

    public Treasure(int x, int y, String imgPath) {
        super(x, y, imgPath);
        super.setType("TREASURE");
        this.worth = 5;
    }
    
    public int getWorth() {
        return worth;
    }
}
