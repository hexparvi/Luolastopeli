/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luolastopeli.logiikka;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javafx.scene.canvas.GraphicsContext;
import luolastopeli.logiikka.entities.EntityManager;
import luolastopeli.logiikka.entities.Player;
import status.StatusDisplay;

/**
 *
 * @author hexparvi
 */
public class Game {
    private Player player;
    private Area currentArea;
    private AreaLoader areaLoader;
    private EntityManager entityManager;
    private StatusDisplay display;
    
    public Game() {
        areaLoader = new AreaLoader();
        entityManager = new EntityManager();
        display = new StatusDisplay();
    }

    public void init() throws FileNotFoundException {
        File mapFile = new File("./src/main/resources/maps/testroom.txt");
        areaLoader.giveMapFile(mapFile);
        areaLoader.load();
        currentArea = new Area(areaLoader.getMap(), areaLoader.getEnemies());
        player = areaLoader.getPlayer();
        entityManager.setGame(this);
        changeArea();
    }
    
    public void changeArea() {
        entityManager.setArea(currentArea);
        entityManager.setEnemies(currentArea.getEnemies());
        entityManager.setPlayer(player);
    }

    public void update(String playerInput) {
        entityManager.updatePlayer(playerInput);
        entityManager.updateEnemies();
    }

    public void drawGame(GraphicsContext gc) {
        gc.clearRect(0, 0, 500, 500);
        currentArea.draw(gc);
    }
    
    public void drawHUD(GraphicsContext gc) {
        gc.clearRect(0, 0, 500, 500);
        display.draw(gc);
    }
    
    public void gameOver() {
        //display game over-screen
    }
    
    public Area getArea() {
        return currentArea;
    }
    
    public Player getPlayer() {
        return player;
    }
    
    public StatusDisplay getStatus() {
        return display;
    }
    
    public boolean isOver() {
        if (player.getHP() == 0) return true;
        if (currentArea.getEnemies().size() == 0) return true;
        return false;
    }

}
