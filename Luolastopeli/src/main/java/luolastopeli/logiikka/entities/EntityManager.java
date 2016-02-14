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
        }
    }

    public void updatePlayer(Player player) {
        
    }

    public void moveEnemies(ArrayList<Enemy> enemy, Area area, Player player) {

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
