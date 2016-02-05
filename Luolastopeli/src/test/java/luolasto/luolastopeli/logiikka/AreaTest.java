/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package luolasto.luolastopeli.logiikka;

import java.io.FileNotFoundException;
import java.util.Scanner;
import luolastopeli.logiikka.Area;
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
        Scanner areasrc = new Scanner("..\n**");
        area = new Area(areasrc);
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
