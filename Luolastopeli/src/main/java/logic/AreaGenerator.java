/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import entities.Enemy;
import entities.Player;
import entities.SpriteEnum;
import java.util.ArrayList;
import java.util.HashMap;
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
    private HashMap<Integer, ArrayList<Integer>> rooms;

    public AreaGenerator(int width, int length) {
        this.areaWidth = width;
        this.areaLength = length;
        this.tilemap = new Tile[width][length];
        enemies = new ArrayList<>();
    }

    public void run() {
        fillTilemap();
        for (int i = 0; i < 5; i++) {
            generateRoom();
        }
    }

    public void fillTilemap() {
        for (int x = 0; x < areaWidth; x++) {
            for (int y = 0; y < areaLength; y++) {
                tilemap[x][y] = new Tile(x, y, "WALL");
            }
        }
    }

    public void generateRoom() {
        Random random = new Random();
        int roomWidth = 3 + random.nextInt(areaWidth / 3);
        int roomLength = 3 + random.nextInt(areaLength / 3);
        
        System.out.println("---Generating room of width " + roomWidth + " and length " + roomLength);

        int x = random.nextInt(areaWidth - (areaWidth / 3));
        int y = random.nextInt(areaLength - (areaLength / 3));
        
        System.out.println("Starting at tile " + x + ", " + y);
        
        if (true) return;
        
        ArrayList<Integer> tmp = new ArrayList<>();
        tmp.add(roomWidth);
        tmp.add(roomLength);
        rooms.put((x + y) * 2, tmp);

        for (int i = x; i < x + roomWidth; i++) {

//            if (i >= areaWidth) {
//                System.out.println("i: " + i + ", areaWidth: " + areaWidth + " -> breaking!");
//                break;
//            }

            for (int j = y; j < y + roomLength; j++) {

//                if (j >= areaLength) {
//                    System.out.println("j: " + j + ", areaLength: " + areaLength + " -> breaking!");
//                    break;
//                }

                //System.out.println("changing tile " + i + ", " + j + " to floor");

                tilemap[i][j] = new Tile(i, j, "FLOOR");

                if (player == null) {
                    player = new Player(i, j, SpriteEnum.PLAYER_SPRITE.getPath());
                    tilemap[i][j].setEntity(player);

                } else {

                    if (Math.random() < 0.1) {
                        Enemy enemy = new Enemy(i, j, SpriteEnum.ENEMY_SPRITE.getPath());
                        tilemap[i][j].setEntity(enemy);
                        enemies.add(enemy);
                    }
                }
            }
        }
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
}
