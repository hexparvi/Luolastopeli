/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import entities.Enemy;
import entities.Player;
import entities.SpriteEnum;
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
    private int areaLength;
    private Tile[][] tilemap;
    private Player player;
    private ArrayList<Enemy> enemies;
    private ArrayList<Treasure> items;
    private ArrayList<Room> rooms;
    private Room prevRoom;
    private boolean[][] prevCells;
    private boolean[][] nextCells;
    private double chanceToStartAlive;

    public AreaGenerator(int width, int length) {
        this.areaWidth = width;
        this.areaLength = length;
        this.tilemap = new Tile[width][length];
        enemies = new ArrayList<>();
        items = new ArrayList<>();
        rooms = new ArrayList<>();
        prevCells = new boolean[width][length];
        chanceToStartAlive = 0.45;
    }

    public void run() {
//        fillTilemap();
//        for (int i = 0; i < 30; i++) {
//            generateRoom();
//        }
        initCells();
        for (int i = 0; i < 30; i++) {
            simStep();
        }
        fillWithTiles();
    }
    
    public void fillWithTiles() {
        Random random = new Random();
        for (int x = 0; x < areaWidth; x++) {
            for (int y = 0; y < areaLength; y++) {
                if (nextCells[x][y]) {
                    tilemap[x][y] = new Tile(x, y, "FLOOR");
                    double number = random.nextDouble();
                    if (player == null) {
                        player = new Player(x, y, SpriteEnum.PLAYER_SPRITE.getPath());
                        tilemap[x][y].setEntity(player);
                    } else if (number < 0.075) {
                        Enemy enemy = new Enemy(x, y, SpriteEnum.ENEMY_SPRITE.getPath());
                        enemies.add(enemy);
                        tilemap[x][y].setEntity(enemy);
                    } else if (number > 0.95) {
                        Treasure treasure = new Treasure(x, y, SpriteEnum.TREASURE_SPRITE.getPath());
                        items.add(treasure);
                        tilemap[x][y].setItem(treasure);
                    }
                } else {
                    tilemap[x][y] = new Tile(x, y, "WALL");
                }
            }
        }
    }

    public void initCells() {
        Random random = new Random();
        for (int x = 0; x < areaWidth; x++) {
            for (int y = 0; y < areaLength; y++) {
                if (random.nextDouble() <= chanceToStartAlive) {
                    prevCells[x][y] = true;
                }
            }
        }
    }

    public void simStep() {
        nextCells = new boolean[areaWidth][areaLength];
        int deathLimit = 2;
        int birthLimit = 3;
        
        for (int x = 1; x < prevCells.length - 1; x++) {
            for (int y = 1; y < prevCells[1].length - 1; y++) {
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
                int neighbour_x = x + i;
                int neighbour_y = y + j;
                //If we're looking at the middle point
                if (i == 0 && j == 0) {
                    //Do nothing, we don't want to add ourselves in!
                } //In case the index we're looking at it off the edge of the map
                else if (neighbour_x < 1 || neighbour_y < 1 || neighbour_x >= prevCells.length - 1 || neighbour_y >= prevCells[1].length - 1) {
                    aliveNeighbors = aliveNeighbors + 1;
                } //Otherwise, a normal check of the neighbour
                else if (prevCells[neighbour_x][neighbour_y]) {
                    aliveNeighbors = aliveNeighbors + 1;
                }
            }
        }
        return aliveNeighbors;
    }

    public void fillTilemap() {
        for (int x = 0; x < areaWidth; x++) {
            for (int y = 0; y < areaLength; y++) {
                tilemap[x][y] = new Tile(x, y, "WALL");
            }
        }
    }

    public void generateVertCorridor(int y1, int y2, int x) {
        int start = Math.min(y1, y2);
        int end = Math.max(y1, y2);
        System.out.println("start, end: " + start + ", " + end);
        System.out.println("x-level: " + x);
        for (int y = start; y <= end; y++) {
            System.out.println("y is now " + y);
            tilemap[x][y] = new Tile(x, y, "FLOOR");
        }
    }

    public void generateHorizCorridor(int x1, int x2, int y) {
        int start = Math.min(x1, x2);
        int end = Math.max(x1, x2);
        System.out.println("start, end: " + start + ", " + end);
        System.out.println("y-level: " + y);
        for (int x = start; x <= end; x++) {
            System.out.println("x is now " + x);
            tilemap[x][y] = new Tile(x, y, "FLOOR");
        }
    }

    public void generateRoom() {
        Random random = new Random();
        int width = 3 + random.nextInt(areaWidth / 5);
        int length = 3 + random.nextInt(areaLength / 5);

        int x = 1 + random.nextInt(areaWidth - (areaWidth / 5) - 1);
        int y = 1 + random.nextInt(areaLength - (areaWidth / 5) - 1);

        Room tmp = new Room(x, y, width, length);

        for (Room room : rooms) {
            if (tmp.intersects(room)) {
                return;
            }
        }

        for (int i = x; i < x + width; i++) {

            if (i >= areaWidth - 1) {
                break;
            }
            for (int j = y; j < y + length; j++) {

                if (j >= areaLength - 1) {
                    break;
                }
                tilemap[i][j] = new Tile(i, j, "FLOOR");
                double number = Math.random();

                if (player == null) {
                    player = new Player(i, j, SpriteEnum.PLAYER_SPRITE.getPath());
                    tilemap[i][j].setEntity(player);

                } else if (number < 0.025 && !tilemap[i][j].hasEntity()) {
                    Enemy enemy = new Enemy(i, j, SpriteEnum.ENEMY_SPRITE.getPath());
                    tilemap[i][j].setEntity(enemy);
                    enemies.add(enemy);

                } else if (number > 0.95 && !tilemap[i][j].hasItem()) {
                    Treasure treasure = new Treasure(i, j, SpriteEnum.TREASURE_SPRITE.getPath());
                    tilemap[i][j].setItem(treasure);
                    items.add(treasure);
                }
            }
        }

        if (!rooms.isEmpty()) {
            System.out.println("");
            System.out.println("prevRoom left x, right x: " + prevRoom.getLeftX() + ", " + prevRoom.getRightX());
            System.out.println("prevRoom top y, bot y: " + prevRoom.getTopY() + ", " + prevRoom.getBotY());

            System.out.println("prevRoom Center x, y: " + prevRoom.getCenterX() + ", " + prevRoom.getCenterY());

            System.out.println("");

            System.out.println("currRoom left x, right x: " + tmp.getLeftX() + ", " + tmp.getRightX());
            System.out.println("currRoom top y, bot y: " + tmp.getTopY() + ", " + tmp.getBotY());

            System.out.println("currRoom Center x, y: " + tmp.getCenterX() + ", " + tmp.getCenterY());
            System.out.println("");

            if (true) {
                generateVertCorridor(prevRoom.getCenterY(), tmp.getCenterY(), prevRoom.getCenterX());
                generateHorizCorridor(prevRoom.getCenterX(), tmp.getCenterX(), prevRoom.getCenterY());
            } else {
                generateVertCorridor(prevRoom.getCenterY(), tmp.getCenterY(), prevRoom.getCenterX());
                generateHorizCorridor(prevRoom.getCenterX(), tmp.getCenterX(), prevRoom.getCenterY());

            }
        }

        prevRoom = tmp;
        rooms.add(tmp);
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
