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
    private ArrayList<Enemy> entities;
    private Player player;
    private Area area;
    
    public EntityManager(ArrayList<Enemy> enemies, Player p, Area map) {
        entities = enemies;
        player = p;
        area = map;
    }
    
    public void updateEntities() {
        for (Enemy entity : entities) {
            entity.moveTowardPlayer(player, area);
        }
    }
    
    public void drawEntities(GraphicsContext gc) {
        for (Enemy entity : entities) {
            gc.drawImage(new Image(entity.getImagePath()), entity.getX() * 32, entity.getY() * 32);
        }
    }
    
    public void drawEntity(Sprite entity, GraphicsContext gc) {
        gc.drawImage(new Image(entity.getImagePath()), entity.getX() * 32, entity.getY() * 32);
    }
}
