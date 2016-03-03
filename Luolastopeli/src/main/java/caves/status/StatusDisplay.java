/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caves.status;

import caves.entities.Actor;
import caves.entities.Player;
import java.util.ArrayList;
import java.util.ListIterator;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * HUD for displaying current player HP, points and recent entity actions.
 *
 * @author hexparvi
 */
public class StatusDisplay {

    private ArrayList<String> messages;
    private Player player;

    public StatusDisplay(Player player) {
        this.player = player;
        this.messages = new ArrayList<>();
    }

    /**
     * Draws HUD.
     * @param gc GraphicsContext to be drawn on
     */
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.CRIMSON);
        gc.fillRect(30, 50, player.getMaxHP() * 10, 10);
        gc.setFill(Color.RED);
        gc.fillRect(30, 50, player.getCurrentHP() * 10, 10);
        
        Font font = Font.font("Verdana", FontWeight.NORMAL, 12);
        gc.setFont(font);
        gc.setFill(Color.BLACK);
        gc.fillText("Points: " + player.getPoints(), 500, 50);
        
        drawMessages(gc);
    }
    
    private void drawMessages(GraphicsContext gc) {
        ListIterator<String> li = messages.listIterator(messages.size());

        int counter = 0;
        while (li.hasPrevious()) {
            gc.setFill(Color.color(0, 0, 0, 1.0 * (Math.pow(0.80, counter))));
            gc.fillText(li.previous(), 400, 600 - counter * 15);
            counter++;
        }
    }

    /**
     * Creates and adds an action message to messages list.
     *
     * @param actor performer of action
     * @param target target of action
     */
    public void statusMessage(Actor actor, Actor target) {
        messages.add(actor.getType() + " hits " + target.getType() + " for " + actor.getDmg() + " dmg!");
        if (messages.size() > 5) {
            messages.remove(0);
        }
    }
}
