/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package luolastopeli.logiikka;

import javafx.scene.canvas.GraphicsContext;
import luolastopeli.logiikka.entities.Sprite;

/**
 *
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
    
    public boolean hasEntity() {
        return entity != null;
    }
    
    public void draw(GraphicsContext gc) {
        
    }
}
