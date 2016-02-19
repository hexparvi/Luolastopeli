/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import entities.Enemy;
import entities.Player;
import entities.Sprite;

/**
 *
 * @author hexparvi
 */
public class Area {

    private Tile[][] tileMap;
    private ArrayList<Enemy> enemies;

    public Area(Tile[][] map, ArrayList<Enemy> entities) {
        tileMap = map;
        enemies = entities;
    }

    public void draw(GraphicsContext gc) {
        for (int i = 0; i < tileMap.length; i++) {
            for (int j = 0; j < tileMap[0].length; j++) {
                tileMap[i][j].draw(gc);
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

    public boolean containsEntity(int x, int y) {
        return tileMap[x][y].hasEntity();
    }

    public Sprite getEntityFromPos(int x, int y) {
        return tileMap[x][y].getEntity();
    }

    public void removeEntityFromPos(int x, int y) {
        tileMap[x][y].removeEntity();
    }

    public void setEntityToPos(int x, int y, Sprite entity) {
        tileMap[x][y].setEntity(entity);
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }
}
