/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caves.entities;

import caves.entities.Player;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hexparvi
 */
public class PlayerTest {
    Player player;
    
    public PlayerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        player = new Player(0, 0);
        player.setMaxHP(10);
        player.setCurrentHP(5);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void healHealsHP() {
        player.heal(4);
        assertEquals(9, player.getCurrentHP());
    }
    
    @Test
    public void healDoesntOverheal() {
        player.heal(8);
        assertEquals(10, player.getCurrentHP());
    }
}
