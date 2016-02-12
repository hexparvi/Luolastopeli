/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luolastopeli.logiikka;

import luolastopeli.logiikka.entities.Enemy;
import luolastopeli.logiikka.entities.Player;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author hexparvi
 */
public class Area {

    private Tile[][] tileMap;
    private ArrayList<Enemy> enemies;
    //private ArrayList<Sprite> treasures;
    
    public Area(Scanner src) throws FileNotFoundException {
//        wallPath = "file:./src/main/resources/images/wallplaceholder.png";
//        floorPath = "file:./src/main/resources/images/floorplaceholder.png";
        AreaLoader loader = new AreaLoader(src);
        loader.load();
        tileMap = loader.getMap();
        enemies = loader.getEnemies();
        //treasures = loader.getTreasures();
    }

    public void draw(GraphicsContext gc) {
        for (int i = 0; i < tileMap.length; i++) {
            for (int j = 0; j < tileMap[0].length; j++) {
                tileMap[i][j].draw(gc);
            }
        }
    }
    
    public boolean isWalkable(int x, int y) {
        if (x >= tileMap.length || y >= tileMap[0].length) return false;
        if (x < 0 || y < 0) return false;
        if (tileMap[x][y].getType().equals("FLOOR") && !tileMap[x][y].hasEntity()) return true;
        return false;
    }
    
    public boolean containsEnemy(int x, int y) {
        return tileMap[x][y].hasEntity();
    }
    
    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }
}
