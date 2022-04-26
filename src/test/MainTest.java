import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.Assert.*;



public class MainTest {
    Land land = new Land();
    Human human = new Human();
    Goblin goblin = new Goblin();
    Chest chest = new Chest();

    public Character arraySearch(char x){
        char[][] p = land.fill;
        char search = ' ';
        for(int c = 0; c < p.length; c++){
            for(int r = 0; r < p.length; r++){
                if(p[c][r] == x){
                    search = x;
                    System.out.println("Match");
                }
            }
        }
        return search;
    }

    @Test
    void rollVariance(){
        int count = 0;
        int match = 0;
        while (count <= 50 && match < 10) {
            int roll = chest.getRoll();
            if(roll == 3){
                match++;
            }
        }

        assertTrue(match == 10);
    }

    @Test
    public void checkPieces(){
        assertTrue("Human not on board", arraySearch('H') == human.getMarker());
        assertTrue("Goblin not on board", arraySearch('G') == goblin.getMarker());
    }

    @Test
    public void humanStrength(){
        human.setWep(human.sword);
        assertTrue("Actual strength: " + human.getStrength(),human.getStrength() == 22);
    }
}
