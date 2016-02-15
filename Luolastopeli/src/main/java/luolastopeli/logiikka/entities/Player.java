/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package luolastopeli.logiikka.entities;

import javafx.scene.image.Image;

/**
 *
 * @author hexparvi
 */
public class Player extends Actor {
    // current map as attribute? maybe for actor class?

    public Player(int initialX, int initialY, String imgPath) {
        super(initialX, initialY, imgPath);
        super.setHP(10);
        super.setDmg(5);
        super.setType("PLAYER");
    }
}
