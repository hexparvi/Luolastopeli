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
import status.StatusDisplay;

/**
 *
 * @author hexparvi
 */
public class Game {

    private Player player;
    private Area currentArea;
    private AreaLoader areaLoader;
    private AreaGenerator areaGen;
    private EntityManager entityManager;
    private StatusDisplay display;
    private State currentState;
    private Group root;
    private Scene scene;
    private HashMap<String, State> states;

    public Game() {
        areaLoader = new AreaLoader();
        entityManager = new EntityManager();
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
//        File mapFile = new File("./src/main/resources/maps/testroom.txt");
//        areaLoader.load(mapFile);
//        currentArea = new Area(areaLoader.getMap(), areaLoader.getEnemies());
//        player = areaLoader.getPlayer();
        
        areaGen = new AreaGenerator(20, 20);
        areaGen.run();
        currentArea = new Area(areaGen.getTilemap(), areaGen.getEnemies());
        player = areaGen.getPlayer();

        display = new StatusDisplay(player);
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

}
