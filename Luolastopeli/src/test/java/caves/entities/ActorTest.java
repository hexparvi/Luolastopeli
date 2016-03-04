/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caves.entities;

import java.util.ArrayList;
import caves.logic.Area;
import caves.logic.Tile;
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
public class ActorTest {

    Player actor;
    Enemy neighbor;
    Enemy notNeighbor;
    Area area;

    public ActorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        actor = new Player(0, 0);
        actor.setMaxHP(10);
        actor.setCurrentHP(10);
        Tile[][] tilemap = new Tile[3][3];
        
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                tilemap[x][y] = new Tile(x, y, "FLOOR");
            }
        }
        
        tilemap[1][1] = new Tile(1, 1, "WALL");
        
        neighbor = new Enemy(1, 0);
        notNeighbor = new Enemy(5, 5);
        
        tilemap[1][0].setEntity(neighbor);
        
        area = new Area(tilemap, new ArrayList<>());
    }

    @After
    public void tearDown() {
    }

    @Test
    public void hpReducedWhenDmgTaken() {
        actor.takeDmg(5);
        assertEquals(5, actor.getCurrentHP());
    }
    
    @Test
    public void hpDoesntGoBelowZero() {
        actor.takeDmg(11);
        assertEquals(0, actor.getCurrentHP());
    }
    
    @Test
    public void negativeDmgTakenDoesntDoAnything() {
        actor.takeDmg(-5);
        assertEquals(10, actor.getCurrentHP());
    }
    
    @Test
    public void moveChangesXY() {
        actor.move("DOWN", area);
        assertEquals(0, actor.getX());
        assertEquals(1, actor.getY());
    }
    
    @Test
    public void cantWalkOnEnemies() {
        actor.move("RIGHT", area);
        assertEquals(0, actor.getX());
        assertEquals(0, actor.getY());
    }
    
    @Test
    public void cantWalkOnWalls() {
        actor.move("DOWN", area);
        actor.move("RIGHT", area);
        assertEquals(0, actor.getX());
        assertEquals(1, actor.getY());
    }
    
    @Test
    public void cantMoveOutsideOfBounds() {
        actor.move("UP", area);
        assertEquals(0, actor.getX());
        assertEquals(0, actor.getY());
    }
    
    @Test
    public void isNextToRecognizesNeighbors() {
        assertTrue(actor.isNextTo(neighbor));
    }
    
    @Test
    public void isNextToReturnsFalseWhenEntityIsNotANeighbor() {
        assertFalse(actor.isNextTo(notNeighbor));
    }
}
