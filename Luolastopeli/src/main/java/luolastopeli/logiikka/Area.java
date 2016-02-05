/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luolastopeli.logiikka;

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

    private char[][] map;
    private int width;
    private int height;
    private Image wall;
    private Image floor;
    private boolean playerIsHere;//maybe?
    private ArrayList<Actor> enemies;
    private ArrayList<Sprite> treasures;
    private Player player;//nullpointerexception potential?

    public Area(Scanner src) throws FileNotFoundException {
        wall = new Image("file:./src/main/resources/images/wallplaceholder.png");
        floor = new Image("file:./src/main/resources/images/floorplaceholder.png");
        AreaLoader loader = new AreaLoader(src);
        loader.load();
        map = loader.getMap();
        width = 12;
        height = 12;
    }

    public void draw(GraphicsContext gc) {
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (map[i][j] == '*') {
                    gc.drawImage(wall, i * 32, j * 32); // store tilesize, sprites etc. where?
                } else {
                    gc.drawImage(floor, i * 32, j * 32);
                }
            }
        }
    }
    
    public boolean isWalkable(int x, int y) {
        if (x >= width || y >= height) return false;
        if (x < 0 || y < 0) return false;
        if (map[x][y] == '.') return true;//walls, floors enemies are not walkable
        return false;
    }
    
    public Player getPlayer() {
        return player;
    }
}
