/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.ArrayList;
import logic.Area;
import logic.Game;

/**
 *
 * @author hexparvi
 */
public class EntityManager {

    private Area area;
    private ArrayList<Enemy> enemies;
    private Player player;
    private Game game;

    public EntityManager() {
    }

    /**
     * For each enemy, handles movement and attacking.
     */
    public void updateEnemies() {
        for (Enemy enemy : enemies) {
            if (enemy.isNextTo(player)) {
                enemy.attack(player);
                game.getStatus().statusMessage(enemy, player);
            }
            String direction = enemy.findDirection(player.getX(), player.getY());
            enemy.move(direction, area);
        }
    }

    /**
     * Handles player movement and attacking.
     *
     * @param input player input as String
     */
    public void updatePlayer(String input) {
        player.move(input, area);

        if (area.containsItem(player.getX(), player.getY())) {
            Treasure item = area.getItemFromPos(player.getX(), player.getY());
            player.increasePoints(item.getWorth());
            player.heal(item.getHealing());
            area.removeItemFromPos(player.getX(), player.getY());
        }

        ArrayList<Actor> neighbors = getNeighbors(player.getX(), player.getY());
        for (Actor neighbor : neighbors) {
            if (neighbor.getType().equals("ENEMY")) {
                boolean targetDied = player.attack(neighbor);
                game.getStatus().statusMessage(player, neighbor);

                if (targetDied) {
                    area.removeEntityFromPos(neighbor.getX(), neighbor.getY());
                    enemies.remove(neighbor);
                }
            }
        }
    }

    /**
     * Gets entities located in neighboring tiles.
     *
     * @param x x-coordinate of the tile
     * @param y y-coordinate of the tile
     * @return list of neighboring Sprites
     */
    private ArrayList<Actor> getNeighbors(int x, int y) {
        ArrayList<Actor> neighbors = new ArrayList<>();
        if (area.containsEntity(x - 1, y)) {
            neighbors.add(area.getEntityFromPos(x - 1, y));
        }
        if (area.containsEntity(x + 1, y)) {
            neighbors.add(area.getEntityFromPos(x + 1, y));
        }
        if (area.containsEntity(x, y - 1)) {
            neighbors.add(area.getEntityFromPos(x, y - 1));
        }
        if (area.containsEntity(x, y + 1)) {
            neighbors.add(area.getEntityFromPos(x, y + 1));
        }
        return neighbors;
    }

    public void setGame(Game newGame) {
        game = newGame;
    }

    public void setEnemies(ArrayList<Enemy> newEnemies) {
        enemies = newEnemies;
    }

    public void setArea(Area newArea) {
        area = newArea;
    }

    public void setPlayer(Player newPlayer) {
        player = newPlayer;
    }
}
