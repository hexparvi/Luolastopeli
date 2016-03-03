/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package caves.logic;

import java.util.ArrayList;
import caves.entities.Enemy;
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
    public void setUp() {
        Tile[][] tilemap = {{new Tile(0, 0, "FLOOR"), new Tile(0, 1, "FLOOR")},
        {new Tile(1, 0, "FLOOR"), new Tile(1, 1, "WALL")}};
        tilemap[1][0].setEntity(new Enemy(1, 0));
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
    
    @Test
    public void isWalkableReturnsFalseReturnsFalseOnEntities() {
        assertFalse(area.isWalkable(1, 0));
    }
    
    @Test
    public void containsEntityReturnsTrueOnEntity() {
        assertTrue(area.containsEntity(1, 0));
    }
    
    @Test
    public void containsEntityReturnsFalseWhenNoEntity() {
        assertFalse(area.containsEntity(1, 1));
    }
    
    @Test
    public void removeEntityRemovesEntity() {
        area.removeEntityFromPos(1, 0);
        assertFalse(area.containsEntity(1, 0));
    }
}
