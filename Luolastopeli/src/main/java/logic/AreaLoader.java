/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import entities.Enemy;
import entities.Player;
import entities.Sprite;

/**
 * Loads Tiles and Entities from a .txt file.
 * @author hexparvi
 */
public class AreaLoader {

    private ArrayList<Enemy> enemies;
    //private ArrayList<Sprite> objects;
    private Player player;
    private Tile[][] map;
    private Scanner scanner;

    public AreaLoader() {
    }
    
    public void giveMapFile(File mapfile) throws FileNotFoundException {
        scanner = new Scanner(mapfile);
    }

    /**
     * Fills map-array with Tiles.
     */
    public void load() {
        map = new Tile[scanner.nextInt()][scanner.nextInt()];
        scanner.nextLine(); // consumes leftover newline character
        enemies = new ArrayList<Enemy>();
        int i = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            for (int j = 0; j < line.length(); j++) {
                checkTileContent(line.charAt(j), i, j);
            }
            i++;
        }
    }
    
    /**
     * Checks tile content.
     * @param c char representation of tile content
     * @param x x-coordinate of tile
     * @param y y-coordinate of tile
     */
    private void checkTileContent(char c, int x, int y) {
        if (c == 'P') {
            player = new Player(x, y, TestSpriteEnum.PLAYER_SPRITE.getPath());
            map[x][y] = new Tile(x, y, "FLOOR");
            map[x][y].setEntity(player);
        } else if (c == 'E') {
            Enemy enemy = new Enemy(x, y, TestSpriteEnum.ENEMY_SPRITE.getPath());
            enemies.add(enemy);
            map[x][y] = new Tile(x, y, "FLOOR");
            map[x][y].setEntity(enemy);
        } else if (c == '.') {
            map[x][y] = new Tile(x, y, "FLOOR");
        } else {
            map[x][y] = new Tile(x, y, "WALL");
        }
    }

    public Tile[][] getMap() {
        return map;
    }

    public Player getPlayer() {
        return player;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

}
