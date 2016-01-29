package luolasto.luolastopeli.logiikka;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Scanner;
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
public class MapLoaderTest {
    
    public MapLoaderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
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
    public void arraySizeIsCorrect() {
        
    }
    
    @Test
    public void loadWorks() {
        MapLoader loader = new MapLoader(new Scanner("**\n.."), 2);
        loader.load();
        char[][] loadedMap = loader.getMap();
        char[][] compare = new char[][]{"**".toCharArray(), "..".toCharArray()};
        assertArrayEquals(compare, loadedMap);
    }
}
