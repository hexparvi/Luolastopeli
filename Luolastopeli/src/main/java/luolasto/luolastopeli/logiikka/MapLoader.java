/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package luolasto.luolastopeli.logiikka;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author hexparvi
 */
public class MapLoader {
    private char[][] map;
    private Scanner scanner;
    
    public MapLoader() throws FileNotFoundException, IOException {
        File mapFile = new File("./src/main/resources/maps/testroom.txt");
        scanner = new Scanner(mapFile);
        map = new char[12][12];
    }
    
    public MapLoader(Scanner scan, int size) {
        scanner = scan;
        map = new char[size][size];
    }
    
    public void load() {
        int i = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            char[] tmp = line.toCharArray();
            map[i] = tmp;
            i++;
        }
    }
    
    public void printMap() {
        for (int i = 0; i < map.length - 1; i++) {
            for (int j = 0; j < map.length; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println("");
        }
    }
    
    public char[][] getMap() {
        return map;
    }
    
}
