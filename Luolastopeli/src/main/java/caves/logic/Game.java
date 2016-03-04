/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caves.logic;

import caves.entities.EntityManager;
import caves.entities.Player;
import caves.gamestates.*;
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
    private EntityManager entityManager;
    private StatusDisplay statusDisplay;
    private State currentState;
    private Group root;
    private Scene scene;
    private HashMap<String, State> states;

    /**
     * Initializes variables.
     */
    public Game() {
        entityManager = new EntityManager();
        root = new Group();
        scene = new Scene(root);
        states = new HashMap<>();
    }

    /**
     * Initializes currentArea, game state, player and entityManager. Sets
     * currentState to menu.
     *
     */
    public void init() {
        generateNewArea();
        changeArea();

        statusDisplay = new StatusDisplay(player);
        entityManager.setGame(this);

        if (states.isEmpty()) {
            createStates();
        }
        setState(getState("MENU"));
    }

    /**
     * Generates a new Area. Sets currentState to play.
     */
    public void restart() {
        init();
        setState(getState("PLAY"));
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

    private void generateNewArea() {
        AreaGenerator areaGen = new AreaGenerator(20, 20);
        areaGen.generate();
        currentArea = new Area(areaGen.getTilemap(), areaGen.getEnemies());
        player = areaGen.getPlayer();
    }

    /**
     * Changes current area and entities for entityManager.
     */
    private void changeArea() {
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
        return statusDisplay;
    }

    public EntityManager getManager() {
        return entityManager;
    }

    public Player getPlayer() {
        return player;
    }

    public StatusDisplay getStatusDisplay() {
        return statusDisplay;
    }

    public State getState(String statename) {
        return states.get(statename);
    }

}
