/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caves.logic;

import caves.entities.EntityManager;
import caves.entities.Player;
import caves.gamestates.GameOver;
import caves.gamestates.Manual;
import caves.gamestates.MainMenu;
import caves.gamestates.Gameplay;
import caves.gamestates.State;
import java.util.HashMap;
import javafx.scene.Group;
import javafx.scene.Scene;
import caves.status.StatusDisplay;

/**
 * Handles gameplay.
 *
 * @author hexparvi
 */
public class Game {

    private Player player;
    private Area currentArea;
    private AreaGenerator areaGen;
    private EntityManager entityManager;
    private StatusDisplay display;
    private State currentState;
    private Group root;
    private Scene scene;
    private HashMap<String, State> states;

    public Game() {
        entityManager = new EntityManager();
        root = new Group();
        scene = new Scene(root);
        states = new HashMap<>();
    }

    /**
     * Initializes currentArea, game state, player and entityManager. Sets currentState to menu.
     *
     */
    public void init() {
        areaGen = new AreaGenerator(20, 20);
        areaGen.run();
        currentArea = new Area(areaGen.getTilemap(), areaGen.getEnemies());
        player = areaGen.getPlayer();

        display = new StatusDisplay(player);
        entityManager.setGame(this);

        if (states.isEmpty()) {
            createStates();
        }
        setState(getState("MENU"));

        changeArea();
    }
    
    /**
     * Generates a new Area. Sets currentState to play.
     */
    public void restart() {
        areaGen = new AreaGenerator(20, 20);
        areaGen.run();
        currentArea = new Area(areaGen.getTilemap(), areaGen.getEnemies());
        player = areaGen.getPlayer();

        display = new StatusDisplay(player);
        setState(getState("PLAY"));

        changeArea();
    }

    /**
     * Creates possible game states and puts them on a list.
     */
    private void createStates() {
        states.put("MENU", new MainMenu(this));
        states.put("MANUAL", new Manual(this));
        states.put("PLAY", new Gameplay(this));
        states.put("END", new GameOver(this));
    }

    /**
     * Changes current area and entities for entityManager.
     */
    public void changeArea() {
        entityManager.setArea(currentArea);
        entityManager.setEnemies(currentArea.getEnemies());
        entityManager.setPlayer(player);
    }

    /**
     * Removes previous state and sets a new one.
     *
     * @param state new State
     */
    public void setState(State state) {
        if (state == null) {
            return;
        }
        if (currentState != null) {
            currentState.removeHandlers(scene);
        }
        currentState = state;
        state.setHandlers(scene);
        root.getChildren().clear();
        root.getChildren().add(state.getGroup());
    }

    /**
     * Update game state.
     */
    public void updateState() {
        currentState.update();
    }

    /**
     * Draw game state.
     */
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

    public State getState(String statename) {
        return states.get(statename);
    }

}
