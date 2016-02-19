/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package luolasto.luolastopeli.logiikka;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import luolastopeli.logiikka.Area;
import luolastopeli.logiikka.Tile;
import luolastopeli.logiikka.entities.Enemy;
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
public class AreaTest {
    Area area;
    
    public AreaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws FileNotFoundException {
        Tile[][] tilemap = {{new Tile(0, 0, "FLOOR"), new Tile(0, 1, "FLOOR")},
        {new Tile(1, 0, "WALL"), new Tile(1, 1, "WALL")}};
        area = new Area(tilemap, new ArrayList<Enemy>());
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void isWalkableReturnsTrueOnFloors() {
        assertTrue(area.isWalkable(0, 0));
    }
    
    @Test
    public void isWalkableReturnsFalseOnWalls() {
        assertFalse(area.isWalkable(1, 1));
    }
    
    @Test
    public void isWalkableReturnsFalseOutsideOfBoundaries() {
        assertFalse(area.isWalkable(-1, -1));
    }
}
