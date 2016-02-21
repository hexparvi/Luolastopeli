/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javafx.scene.image.Image;

/**
 *
 * @author hexparvi
 */
public enum SpriteEnum {

    PLAYER_SPRITE("file:./src/main/resources/images/playerplaceholder.png"),
    ENEMY_SPRITE("file:./src/main/resources/images/enemyplaceholder.png"),
    FLOOR_SPRITE("file:./src/main/resources/images/floorplaceholder.png"),
    WALL_SPRITE("file:./src/main/resources/images/wallplaceholder.png");

    private String filePath;
    private Image image;
    private boolean imageLoaded;

    SpriteEnum(String imagePath) {
        filePath = imagePath;
        imageLoaded = false;
    }

    /**
     * Creates a new Image object based on file path.
     */
    public void loadImage() {
        image = new Image(this.filePath);
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
        return filePath;
    }
}