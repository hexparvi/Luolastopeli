/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package status;

import java.util.ArrayList;
import java.util.ListIterator;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import entities.Actor;
import entities.Player;

/**
 *
 * @author hexparvi
 */
public class StatusDisplay {

    private int maxHP;
    private int currentHP;
    private ArrayList<String> messages;

    public StatusDisplay() {
        maxHP = 10;
        currentHP = 10;
        messages = new ArrayList<>();
    }

    public void draw(GraphicsContext gc) {
        // separate stuff to methods (drawHP, drawMsg etc.)
        gc.setFill(Color.BLACK);
        gc.fillRect(20, 30, 100, 10);
        gc.setFill(Color.RED);
        gc.fillRect(20, 30, currentHP * 10, 10);
        
        ListIterator<String> li = messages.listIterator(messages.size());
        
        int counter = 0;
        while (li.hasPrevious()) {
            gc.setFill(Color.color(0, 0, 0, 1.0 * (Math.pow(0.80, counter))));
            gc.fillText(li.previous(), 200, 300 - counter * 15);
            counter++;
        }
    }

    public void update(Player player) {
        currentHP = player.getHP();
    }

    public void statusMessage(Actor actor, Actor target) {
        messages.add(actor.getType() + " hits " + target.getType() + " for " + actor.getDmg() + " dmg!");
        if (messages.size() > 5) {
            messages.remove(0);
        }
    }
}
