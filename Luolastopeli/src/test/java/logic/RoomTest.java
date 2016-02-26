/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

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
public class RoomTest {
    Room room1;
    Room room2;
    Room room3;
    
    public RoomTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        room1 = new Room(0, 0, 5, 5);
        room2 = new Room(0, 0, 5, 5);
        room3 = new Room(6, 6, 5, 5);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void intersectsReturnsTrueWhenRoomsOverlap() {
        assertTrue(room1.intersects(room2));
    }
    
    @Test
    public void intersectsReturnsFalseWhenRoomsDontOverlap() {
        assertFalse(room1.intersects(room3));
    }
}
