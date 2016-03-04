/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caves.entities;

import java.util.ArrayList;
import caves.logic.Area;
import caves.logic.Game;

/**
 * Handles entity movement and status changes.
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
            pickUpItem();
        }

        int frontX = player.getX();
        int frontY = player.getY();

        switch (input) {
            case "UP":
                frontY--;
                break;
            case "DOWN":
                frontY++;
                break;
            case "LEFT":
                frontX--;
                break;
            case "RIGHT":
                frontX++;
                break;
        }

        if (area.containsEntity(frontX, frontY)) {
            attack(frontX, frontY);
        }
    }

    private void pickUpItem() {
        Treasure item = area.getItemFromPos(player.getX(), player.getY());
        player.increasePoints(item.getWorth());
        player.heal(item.getHealing());
        area.removeItemFromPos(player.getX(), player.getY());
    }

    private void attack(int x, int y) {
        Actor target = area.getEntityFromPos(x, y);
        if (target.getType().equals("ENEMY")) {
            boolean targetDied = player.attack(target);
            game.getStatus().statusMessage(player, target);

            if (targetDied) {
                area.removeEntityFromPos(target.getX(), target.getY());
                enemies.remove(target);
            }
        }
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
