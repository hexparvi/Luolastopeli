/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luolastopeli.logiikka;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;

/**
 *
 * @author hexparvi
 */
public class AreaLoader {
    private ArrayList<Actor> enemies;
    private ArrayList<Sprite> objects;
    private Player player;
    private char[][] map;
    private Scanner scanner;

    public AreaLoader() throws FileNotFoundException {
        File mapFile = new File("./src/main/resources/maps/testroom.txt");
        scanner = new Scanner(mapFile);
        map = new char[12][12];
        
        enemies = new ArrayList<Actor>();
        objects = new ArrayList<Sprite>();
    }

    public AreaLoader(Scanner scan) {
        scanner = scan;
        // finding map size? values at start of map file? standard size?
        map = new char[30][30];
    }

    public void load() {
        int i = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            char[] tmp = line.toCharArray();
            map[i] = tmp;
            i++;
        }
    }
    
    public void loadAlt() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                char tmp = scanner.next().charAt(0);
                if (tmp == 'P') {
                    player = new Player(i, j, new Image("file:./src/main/resources/images/playerplaceholder.png"));
                    map[i][j] = '.';
                } else if (tmp == 'E') {
                    enemies.add(new Enemy(i, j, new Image("file:./src/main/resources/images/playerplaceholder.png")));
                    map[i][j] = '.';
                } else {
                    map[i][j] = tmp;
                }
            }
        }
    }

    public char[][] getMap() {
        return map;
    }
    
    public Player getPlayer() {
        return player;
    }
    
    public ArrayList<Actor> getEnemies() {
        return enemies;
    }
    
    public ArrayList<Sprite> getObjects() {
        return objects;
    }

}
