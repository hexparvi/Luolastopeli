/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
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
            String direction = enemy.findDirection(player.getX(), player.getY());
            enemy.move(direction, area);
            if (enemy.isNextTo(player)) {
                enemy.attack(player);
                game.getStatus().statusMessage(enemy, player);
                game.getStatus().update(player);
            }
        }
    }

    /**
     * Handles player movement and attacking.
     * @param input player input as String
     */
    public void updatePlayer(String input) {
        player.move(input, area);
        ArrayList<Sprite> neighbors = getNeighbors(player.getX(), player.getY());
        for (Sprite neighbor : neighbors) {
            if (neighbor.getType().equals("ENEMY")) {
                boolean targetDied = player.attack((Actor) neighbor);
                game.getStatus().statusMessage(player, (Actor) neighbor); // eww typecasts
                if (targetDied) {
                    area.removeEntityFromPos(neighbor.getX(), neighbor.getY());
                    enemies.remove(neighbor);
                }
            }
        }
    }

    public void moveEnemies() {

    }
    
    /**
     * Gets entities located in neighboring tiles.
     * @param x x-coordinate of the tile
     * @param y y-coordinate of the tile
     * @return list of neighboring Sprites
     */
    private ArrayList<Sprite> getNeighbors(int x, int y) {
        ArrayList<Sprite> neighbors = new ArrayList<>();
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

    private void sendMessages() {
        game.getStatus();
    }
}
