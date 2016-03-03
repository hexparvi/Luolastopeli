/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caves.entities;

import java.io.InputStream;
import javafx.scene.image.Image;

/**
 *Stores file paths and loads sprite images.
 * @author hexparvi
 */
public enum SpriteEnum {

    PLAYER_SPRITE("images/playerplaceholder.png"),
    ENEMY_SPRITE("images/enemyplaceholder.png"),
    FLOOR_SPRITE("images/floorplaceholder.png"),
    WALL_SPRITE("images/wallplaceholder.png"),
    TREASURE_SPRITE("images/treasureplaceholder.png");

    private String imagePath;
    private Image image;
    private boolean imageLoaded;

    SpriteEnum(String imagePath) {
        this.imagePath = imagePath;
        imageLoaded = false;
    }

    /**
     * Creates a new Image object based on file path.
     */
    public void loadImage() {
        image = new Image(imagePath);
        imageLoaded = true;
    }
    
    /**
     * Loads and returns an Image object based on file path.
     * @return Image object
     */
    public Image getImage() {
        if (imageLoaded == false) {
            loadImage();
        }
        return image;
    }

    public String getPath() {
        return imagePath;
    }
}
