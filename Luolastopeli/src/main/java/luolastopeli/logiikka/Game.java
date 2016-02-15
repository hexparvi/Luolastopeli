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

/**
 *
 * @author hexparvi
 */
public class Game {
    private Player player;
    private Area currentArea;
    private AreaLoader areaLoader;
    private EntityManager entityManager;
    
    public Game() {
        areaLoader = new AreaLoader();
        entityManager = new EntityManager();
    }

    public void init() throws FileNotFoundException {
        File mapFile = new File("./src/main/resources/maps/testroom.txt");
        areaLoader.giveMapFile(mapFile);
        areaLoader.load();
        currentArea = new Area(areaLoader.getMap(), areaLoader.getEnemies());
        player = areaLoader.getPlayer();
    }

    public void update(String playerInput) {
        entityManager.updatePlayer(player, currentArea, playerInput);
        entityManager.updateEnemies(currentArea.getEnemies(), currentArea, player);
    }

    public void draw(GraphicsContext gc) {
        gc.clearRect(0, 0, 500, 500);
        currentArea.draw(gc);
    }

}
