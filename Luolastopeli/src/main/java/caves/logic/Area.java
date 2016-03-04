/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caves.logic;

import caves.entities.Actor;
import caves.entities.Enemy;
import caves.entities.Treasure;
import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;

/**
 *Stores and provides access to game tiles as a 2d array.
 * @author hexparvi
 */
public class Area {

    private Tile[][] tileMap;
    private ArrayList<Enemy> enemies;

    /**
     * Sets tileMap and enemies.
     * @param tileMap a 2d array containing Tiles for the Area
     * @param enemies a list of enemies for the Area
     */
    public Area(Tile[][] tileMap, ArrayList<Enemy> enemies) {
        this.tileMap = tileMap;
        this.enemies = enemies;
    }

    /**
     * Draws tiles in tileMap.
     * @param gc GraphicsContext to be drawn on
     */
    public void draw(GraphicsContext gc) {
        for (int i = 0; i < tileMap.length; i++) {
            for (int j = 0; j < tileMap[0].length; j++) {
                tileMap[i][j].drawTile(gc);
            }
        }
    }
    
    /**
     * Checks if tile is walkable.
     * @param x x-coordinate of tile to be checked
     * @param y y-coordinate of tile to be checked
     * @return true if tile is walkable, false otherwise
     */
    public boolean isWalkable(int x, int y) {
        if (x >= tileMap.length || y >= tileMap[0].length) {
            return false;
        }
        if (x < 0 || y < 0) {
            return false;
        }
        if (tileMap[x][y].getType().equals("FLOOR") && !tileMap[x][y].hasEntity()) {
            return true;
        }
        return false;
    }
    
    /**
     * Checks if tile at x, y contains an entity.
     * @param x x-coordinate of tile
     * @param y y-coordinate of tile
     * @return true if tile contains an entity, false otherwise
     */
    public boolean containsEntity(int x, int y) {
        return tileMap[x][y].hasEntity();
    }

    /**
     * Returns entity from tile located at x, y.
     * @param x x-coordinate of tile
     * @param y y-coordinate of tile
     * @return Actor set to tile
     */
    public Actor getEntityFromPos(int x, int y) {
        return tileMap[x][y].getEntity();
    }

    /**
     * Removes entity from tile located at x, y.
     * @param x x-coordinate of tile
     * @param y y-coordinate of tile
     */
    public void removeEntityFromPos(int x, int y) {
        tileMap[x][y].removeEntity();
    }

    /**
     * Sets entity to tile located at x, y.
     * @param x x-coordinate of tile
     * @param y y-coordinate of tile
     * @param entity Actor to be set to tile
     */
    public void setEntityToPos(int x, int y, Actor entity) {
        tileMap[x][y].setEntity(entity);
    }
    
    /**
     * Checks if tile at x, y contains an item.
     * @param x x-coordinate of tile
     * @param y y-coordinate of tile
     * @return true if tile contains an item, false otherwise
     */
    public boolean containsItem(int x, int y) {
        return tileMap[x][y].hasItem();
    }
    
    /**
     * Returns item from tile located at x, y.
     * @param x x-coordinate of tile
     * @param y y-coordinate of tile
     * @return Treasure set to tile
     */
    public Treasure getItemFromPos(int x, int y) {
        return tileMap[x][y].getItem();
    }
    
    /**
     * Removes item from tile located at x, y.
     * @param x x-coordinate of tile
     * @param y y-coordinate of tile
     */
    public void removeItemFromPos(int x, int y) {
        tileMap[x][y].removeItem();
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }
}
