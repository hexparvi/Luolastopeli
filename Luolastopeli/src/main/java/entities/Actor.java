/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import logic.Area;

/**
 *Handles movement and HP of actors.
 * @author hexparvi
 */
abstract public class Actor extends Sprite {

    private int speed; // number of steps taken in one turn
    private int maxHP;
    private int currentHP;
    private int dmg;

    protected Actor(int x, int y, String imgPath) {
        super(x, y, imgPath);
        speed = 1;
    }
    
    public int getMaxHP() {
        return maxHP;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public void setMaxHP(int hp) {
        maxHP = hp;
    }
    
    public void setCurrentHP(int hp) {
        currentHP = hp;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }

    public int getDmg() {
        return dmg;
    }
    
     /**
     * Moves actor in area.
     * @param direction direction of movement
     * @param area area in which actor is located
     */
    public void move(String direction, Area area) {
        area.removeEntityFromPos(x, y);
        switch (direction) {
            case "UP":
                if (area.isWalkable(x, y - speed)) {
                    y = y - speed;
                }
                break;
            case "DOWN":
                if (area.isWalkable(x, y + speed)) {
                    y = y + speed;
                }
                break;
            case "LEFT":
                if (area.isWalkable(x - speed, y)) {
                    x = x - speed;
                }
                break;
            case "RIGHT":
                if (area.isWalkable(x + speed, y)) {
                    x = x + speed;
                }
                break;
        }
        area.setEntityToPos(x, y, this);
    }
    
    /**
     * Reduces caller HP or sets it to zero if damageTaken is greater than currentHP.
     * 
     * @param dmgTaken damage
     * 
     * @return true if caller died, false otherwise
     */
    public boolean takeDmg(int dmgTaken) {
        if (dmgTaken > currentHP) {
            currentHP = 0;
            return true;
        } else {
            currentHP -= dmgTaken;
            return false;
        }
    }

    /**
     * Attacks target.
     * @param target
     * @return true if target died, false otherwise
     */
    public boolean attack(Actor target) {
        return target.takeDmg(this.dmg);
    }

    /**
     * Checks if caller is next to entity horizontally or vertically.
     * @param entity
     * @return true if caller is next to entity, false otherwise
     */
    public boolean isNextTo(Sprite entity) {
        int entityX = entity.getX();
        int entityY = entity.getY();
        if (entityX == x && (entityY == y - 1 || entityY == y + 1)) {
            return true;
        }
        if (entityY == y && (entityX == x - 1 || entityX == x + 1)) {
            return true;
        }
        return false;
    }

}
