/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.ArrayList;
import logic.Area;
import logic.Tile;
import entities.Enemy;
import entities.Player;
import logic.Area;
import logic.Tile;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author hexparvi
 */
public class SpriteTest {

    Area area;
    Player player;

    public SpriteTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        Tile[][] tilemap = {{new Tile(0, 0, "FLOOR"), new Tile(0, 1, "FLOOR"), new Tile(0, 2, "FLOOR")},
        {new Tile(1, 0, "FLOOR"), new Tile(1, 1, "WALL"), new Tile(1, 2, "WALL")},
        {new Tile(2, 0, "FLOOR"), new Tile(2, 1, "FLOOR"), new Tile(2, 2, "FLOOR")}};
        area = new Area(tilemap, new ArrayList<Enemy>());
        player = new Player(1, 0);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void movingOneStepChangesXYCorrectly() {
        player.move("RIGHT", area);
        assertEquals(2, player.getX());
        assertEquals(0, player.getY());
    }

    @Test
    public void movingMultipleStepsChangesXYCorrectly() {
        player.move("LEFT", area);
        player.move("DOWN", area);
        assertEquals(0, player.getX());
        assertEquals(1, player.getY());
    }
    
    @Test
    public void actorCantTeleportThroughWalls() {
        player.move("DOWN", area);
        assertEquals(1, player.getX());
        assertEquals(0, player.getY());
    }
    
    @Test
    public void cantMoveOnTopOfNonWalkableTiles() {
        player.move("DOWN", area);
        assertEquals(1, player.getX());
        assertEquals(0, player.getY());
    }
}
