/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import entities.EntityManager;
import entities.Player;
import gamestates.EndState;
import gamestates.PlayState;
import gamestates.State;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
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
    private State currentState;
    private Group root;
    private Scene scene;
    private HashMap<String, State> states;

    public Game() {
        areaLoader = new AreaLoader();
        entityManager = new EntityManager();
        display = new StatusDisplay();
        root = new Group();
        scene = new Scene(root);
        states = new HashMap<>();
    }

    /**
     * Initializes currentArea, player and entityManager.
     *
     * @throws FileNotFoundException
     */
    public void init() throws FileNotFoundException {
        File mapFile = new File("./src/main/resources/maps/testroom.txt");
        areaLoader.giveMapFile(mapFile);
        areaLoader.load();
        currentArea = new Area(areaLoader.getMap(), areaLoader.getEnemies());
        player = areaLoader.getPlayer();
        entityManager.setGame(this);
        
        if (states.isEmpty()) {
            createStates();
        }
        setState(states.get("PLAY"));
        
        changeArea();
    }

    private void createStates() {
        states.put("PLAY", new PlayState(this));
        states.put("END", new EndState(this));
    }

    /**
     * Changes current area and entities for entityManager.
     */
    public void changeArea() {
        entityManager.setArea(currentArea);
        entityManager.setEnemies(currentArea.getEnemies());
        entityManager.setPlayer(player);
    }

    public void setState(State state) {
        if (currentState != null) {
            currentState.removeHandlers(scene);
        }
        currentState = state;
        state.setHandlers(scene);
        root.getChildren().clear();
        root.getChildren().add(state.getGroup());
    }

    public void updateState() {
        currentState.update();
    }

    public void drawState() {
        currentState.draw();
    }

    public void restart() throws FileNotFoundException {
        this.init();
    }

    public void gameOver() {
        //display game over-screen
    }

    public Area getArea() {
        return currentArea;
    }

    public Scene getScene() {
        return scene;
    }

    public StatusDisplay getDisplay() {
        return display;
    }

    public EntityManager getManager() {
        return entityManager;
    }

    public Player getPlayer() {
        return player;
    }

    public StatusDisplay getStatus() {
        return display;
    }
    
    public HashMap<String, State> getStates() {
        return states;
    }

    /**
     * Checks if game is over.
     *
     * @return true if either player or all enemies are dead, false otherwise
     */
    public boolean isOver() {
        if (player.getHP() == 0) {
            return true;
        }
        if (currentArea.getEnemies().isEmpty()) {
            return true;
        }
        return false;
    }

}
