/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

/**
 *Stores room coordinates for level generation.
 * @author hexparvi
 */
public class Room {
    private int leftX;
    private int rightX;
    private int topY;
    private int botY;
    private int centerX;
    private int centerY;
    
    public Room(int startingX, int startingY, int width, int length) {
        leftX = startingX;
        rightX = startingX + width;
        topY = startingY;
        botY = startingY + length;
        centerX = (rightX + leftX) / 2;
        centerY = (botY + topY) / 2;
    }
    
    public int getLeftX() {
        return leftX;
    }
    
    public int getRightX() {
        return rightX;
    }
    
    public int getBotY() {
        return botY;
    }
    
    public int getTopY() {
        return topY;
    }
    
    public int getCenterX() {
        return centerX;
    }
    
    public int getCenterY() {
        return centerY;
    }
    
    /**
     * Checks if two rooms intersect each other.
     * @param room
     * @return true if rooms intersect, false otherwise
     */
    public boolean intersects(Room room) {
        if (leftX >= room.getRightX() + 1
                || rightX + 1 <= room.getLeftX()
                || topY >= room.getBotY() + 1
                || botY + 1 <= room.getTopY()) {
            return false;
        }
        return true;
    }
}
