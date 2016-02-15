/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luolastopeli.logiikka.entities;

import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import luolastopeli.logiikka.Area;

/**
 *
 * @author hexparvi
 */
public class EntityManager {

    public EntityManager() {
    }

    public void updateEnemies(ArrayList<Enemy> enemies, Area area, Player player) {
        for (Enemy enemy : enemies) {
            String direction = enemy.findDirection(player.getX(), player.getY());
            enemy.move(direction, area);
            if (enemy.isNextTo(player)) {
                boolean targetDied = enemy.attack(player);
                if (targetDied) {
                    //game over
                }
            }
        }
    }

    public void updatePlayer(Player player, Area area, String input) {
        player.move(input, area);
        ArrayList<Sprite> neighbors = getNeighbors(area, player.getX(), player.getY());
        for (Sprite neighbor : neighbors) {
            player.interact(neighbor); // only attacks one target per round for some reason?
        }
    }

    public void moveEnemies(ArrayList<Enemy> enemy, Area area, Player player) {

    }
    
    private ArrayList<Sprite> getNeighbors(Area area, int x, int y) {
        ArrayList<Sprite> neighbors = new ArrayList<>();
        if (area.containsEntity(x - 1, y)) {
            neighbors.add(area.getEntityFromPos(x - 1, y));
        }
        if (area.containsEntity(x + 1, y)) {
            neighbors.add(area.getEntityFromPos(x + 1, y));
        }
        if (area.containsEntity(x, y - 1)) {
            neighbors.add(area.getEntityFromPos(x, y - 1));
        }
        if (area.containsEntity(x, y + 1)) {
            neighbors.add(area.getEntityFromPos(x, y + 1));
        }
        return neighbors;
    }

//    public void drawEntities(GraphicsContext gc) {
//        for (Enemy entity : entities) {
//            gc.drawImage(new Image(entity.getImagePath()), entity.getX() * 32, entity.getY() * 32);
//        }
//    }
//    
//    public void drawEntity(Sprite entity, GraphicsContext gc) {
//        gc.drawImage(new Image(entity.getImagePath()), entity.getX() * 32, entity.getY() * 32);
//    }
}
