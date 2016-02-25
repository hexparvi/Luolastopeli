/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import entities.Actor;
import entities.Sprite;
import entities.SpriteEnum;
import entities.Treasure;
import javafx.scene.canvas.GraphicsContext;

/**
 * Container for tile type and an entity.
 *
 * @author hexparvi
 */
public class Tile {

    private final int x;
    private final int y;
    private Actor entity;
    private Treasure item;
    private String type;

    public Tile(int x, int y, String type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setEntity(Actor entity) {
        this.entity = entity;
    }

    public Actor getEntity() {
        return entity;
    }

    public void removeEntity() {
        entity = null;
    }

    public boolean hasEntity() {
        return entity != null;
    }

    public void setItem(Treasure item) {
        this.item = item;
    }

    public Treasure getItem() {
        return item;
    }

    public void removeItem() {
        this.item = null;
    }

    public boolean hasItem() {
        return item != null;
    }

    public void drawTile(GraphicsContext gc) {
        switch (type) {
            case "FLOOR":
                gc.drawImage(SpriteEnum.FLOOR_SPRITE.getImage(), x * 32, y * 32);
                break;
            case "WALL":
                gc.drawImage(SpriteEnum.WALL_SPRITE.getImage(), x * 32, y * 32);
                break;
        }

        if (hasItem()) {
            gc.drawImage(SpriteEnum.TREASURE_SPRITE.getImage(), x * 32, y * 32);
        }

        if (hasEntity()) {
            if (getEntity().getType().equals("PLAYER")) {
                gc.drawImage(SpriteEnum.PLAYER_SPRITE.getImage(), x * 32, y * 32);

            } else {
                gc.drawImage(SpriteEnum.ENEMY_SPRITE.getImage(), x * 32, y * 32);
            }
        }
    }
}
