/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luolasto.luolastopeli.logiikka;

import java.io.FileNotFoundException;
import java.util.Scanner;
import luolastopeli.logiikka.Area;
import luolastopeli.logiikka.Player;
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
    public void setUp() throws FileNotFoundException {
        Scanner scanner = new Scanner(".P.\n.*.\n...\n...");
        area = new Area(scanner);
        actor = area.getPlayer();
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void movingOneStepChangesXYCorrectly() {
        actor.move("RIGHT", 1, area);
        assertEquals(2, actor.getX());
        assertEquals(0, actor.getY());
    }

    @Test
    public void movingMultipleStepsChangesXYCorrectly() {
        actor.move("LEFT", 1, area);
        actor.move("DOWN", 2, area);
        assertEquals(0, actor.getX());
        assertEquals(2, actor.getY());
    }
    
    @Test
    public void actorCantTeleportThroughWalls() {
        actor.move("DOWN", 2, area);
        assertEquals(1, actor.getX());
        assertEquals(0, actor.getY());
    }
    
    @Test
    public void cantMoveOnTopOfNonWalkableTiles() {
        actor.move("DOWN", 1, area);
        assertEquals(1, actor.getX());
        assertEquals(0, actor.getY());
    }
    
    @Test
    public void hpReducedWhenDmgTaken() {
        actor.takeDmg(5);
        assertEquals(5, actor.getHP());
    }
    
    @Test
    public void hpDoesntGoBelowZero() {
        actor.takeDmg(11);
        assertEquals(0, actor.getHP());
    }
}
