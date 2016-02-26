/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import entities.Enemy;
import entities.Player;
import entities.Treasure;
import java.util.ArrayList;
import java.util.Random;

/**
 * Randomly generates a new Area.
 *
 * @author hexparvi
 */
public class AreaGenerator {

    private int areaWidth;
    private int areaHeight;
    private Tile[][] tilemap;
    private Player player;
    private ArrayList<Enemy> enemies;
    private ArrayList<Treasure> items;
    private boolean[][] prevCells;
    private boolean[][] nextCells;
    private double chanceToStartAlive;

    public AreaGenerator(int width, int height) {
        this.areaWidth = width;
        this.areaHeight = height;
        this.tilemap = new Tile[width][height];
        enemies = new ArrayList<>();
        items = new ArrayList<>();
        prevCells = new boolean[width][height];
        chanceToStartAlive = 0.45;
    }

    public void run() {
        initCells();
        for (int i = 0; i < 30; i++) {
            simulateStep();
        }
        fillWithTiles();
    }
    
    public void fillWithTiles() {
        Random random = new Random();
        
        for (int x = 0; x < areaWidth; x++) {
            for (int y = 0; y < areaHeight; y++) {
                
                if (nextCells[x][y]) { // cell is alive, place floor
                    tilemap[x][y] = new Tile(x, y, "FLOOR");
                    double number = random.nextDouble();
                    
                    if (player == null) {
                        player = new Player(x, y);
                        tilemap[x][y].setEntity(player);
                        
                    } else if (number < 0.075) {
                        Enemy enemy = new Enemy(x, y);
                        enemies.add(enemy);
                        tilemap[x][y].setEntity(enemy);
                        
                    } else if (number > 0.95) {
                        Treasure treasure = new Treasure(x, y);
                        items.add(treasure);
                        tilemap[x][y].setItem(treasure);
                    }
                    
                } else { // cell is dead, place wall
                    tilemap[x][y] = new Tile(x, y, "WALL");
                }
            }
        }
    }

    public void initCells() {
        Random random = new Random();
        for (int x = 0; x < areaWidth; x++) {
            for (int y = 0; y < areaHeight; y++) {
                if (random.nextDouble() <= chanceToStartAlive) {
                    prevCells[x][y] = true;
                }
            }
        }
    }

    public void simulateStep() { // one step of game of life
        nextCells = new boolean[areaWidth][areaHeight];
        int deathLimit = 2;
        int birthLimit = 3;
        
        for (int x = 1; x < areaWidth - 1; x++) {
            for (int y = 1; y < areaHeight - 1; y++) {
                int nbs = countAliveNeighbors(x, y);
                if (prevCells[x][y]) {
                    if (nbs < deathLimit) {
                        nextCells[x][y] = false;
                    } else {
                        nextCells[x][y] = true;
                    }
                }
                else {
                    if (nbs > birthLimit) {
                        nextCells[x][y] = true;
                    } else {
                        nextCells[x][y] = false;
                    }
                }
            }
        }

    }

    public int countAliveNeighbors(int x, int y) {
        int aliveNeighbors = 0;
        
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                int nbX = x + i;
                int nbY = y + j;
                if (i == 0 && j == 0) {
                    continue;
                } else if (nbX < 1 || nbY < 1 || nbX >= areaWidth - 1 || nbY >= areaHeight - 1) {
                    aliveNeighbors = aliveNeighbors + 1; // count spaces outside of area as living
                    
                } else if (prevCells[nbX][nbY]) {
                    aliveNeighbors = aliveNeighbors + 1;
                }
            }
        }
        return aliveNeighbors;
    }

    public Tile[][] getTilemap() {
        return tilemap;
    }

    public Player getPlayer() {
        return player;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public ArrayList<Treasure> getItems() {
        return items;
    }
}
