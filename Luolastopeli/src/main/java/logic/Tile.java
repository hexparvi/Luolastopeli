/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import entities.SpriteEnum;
import javafx.scene.canvas.GraphicsContext;
import entities.Sprite;

/**
 * Container for tile type and an entity.
 * @author hexparvi
 */
public class Tile {

    private int x;
    private int y;
    private Sprite entity;
    private String type;

    public Tile(int xPos, int yPos, String initType) {
        x = xPos;
        y = yPos;
        type = initType;
    }

    public void setType(String newType) {
        type = newType;
    }

    public String getType() {
        return type;
    }

    public void setEntity(Sprite newEntity) {
        entity = newEntity;
    }

    public Sprite getEntity() {
        return entity;
    }

    public void removeEntity() {
        entity = null;
    }

    public boolean hasEntity() {
        return entity != null;
    }

    public void draw(GraphicsContext gc) {
        switch (type) {
            case "FLOOR":
                gc.drawImage(SpriteEnum.FLOOR_SPRITE.getImage(), x * 32, y * 32);
                break;
            case "WALL":
                gc.drawImage(SpriteEnum.WALL_SPRITE.getImage(), x * 32, y * 32);
                break;
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
