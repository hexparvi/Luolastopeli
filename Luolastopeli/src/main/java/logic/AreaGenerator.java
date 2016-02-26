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
    private ArrayList<Treasure> items;
    private ArrayList<Room> rooms;

    public AreaGenerator(int width, int length) {
        this.areaWidth = width;
        this.areaLength = length;
        this.tilemap = new Tile[width][length];
        enemies = new ArrayList<>();
        items = new ArrayList<>();
        rooms = new ArrayList<>();
    }

    public void run() {
        fillTilemap();
        for (int i = 0; i < 30; i++) {
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

        rooms.add(tmp);

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
