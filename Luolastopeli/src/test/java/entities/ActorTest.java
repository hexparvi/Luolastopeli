/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import logic.Area;
import logic.Tile;
import entities.Enemy;
import entities.Player;
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
        actor = new Player(0, 0, "");
        actor.setCurrentHP(10);
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
}
