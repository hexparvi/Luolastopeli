/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import javafx.scene.image.Image;

/**
 *
 * @author hexparvi
 */
public class Player extends Actor {

    public Player(int initialX, int initialY, String imgPath) {
        super(initialX, initialY, imgPath);
        super.setHP(10);
        super.setDmg(5);
        super.setType("PLAYER");
    }
}
