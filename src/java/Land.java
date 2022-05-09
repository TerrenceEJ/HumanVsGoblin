import java.util.concurrent.ThreadLocalRandom;
import static java.lang.System.exit;

public class Land {
    Human h = new Human();
    Goblin g = new Goblin();
    Potion p = new Potion();
    Chest chest = new Chest();
    char[][] fill = new char[10][10]; //grid that were working with
    int size = fill.length-1;
    public static int battles = 0;

    public Land(){
        for(int i = 0; i < fill.length; i++){
            for(int j = 0; j < fill.length; j++){
                if(i == 0 && j == 0){
                    fill[i][j] = h.getMarker(); //initialize human location
                }
                else if(i == size && j == size){
                    fill[i][j] = g.getMarker(); //initialize goblin location
                }
                else{
                    fill[i][j] = '-';
                }
            }
        }
    }

    @Override
    public String toString(){
        return ("Current map layout:" + getCurrent(fill));
    }

    public String getCurrent(char[][] x){
        String list = "";
        for(int col = 0; col < fill.length; col++){
            list += "\n";
            for(int row = 0; row < fill.length; row++){
                list += fill[col][row];
            }
        }
        return list;
    }

    public void initChest(){
        //roll coords for chest location and make sure they dont land on goblin or human
        int rollC1 = ThreadLocalRandom.current().nextInt(0,size);
        int rollC2 = ThreadLocalRandom.current().nextInt(0,size);
        int fC = getHumIndex().getFirst();
        int fR = getHumIndex().getFirst();

        if((rollC1 != size && rollC2 != size) && (rollC1 != fC && rollC2 != fR) && !fill.equals(chest.getMarker())){ //goblin respawned only need to check spawn point, check for human icon overlap
            fill[rollC1][rollC2] = chest.getMarker();
        }
    }

    public IndexPair getHumIndex(){
        char[][] l = fill;
        int index = 0;
        int index2 = 0;
        for(int col = 0; col < l.length; col++){

            for(int row = 0; row < l.length; row++){
                if(l[col][row] == h.getMarker()){
                    index = col;
                    index2 = row;
                }
            }
        }
        if(l[index][index2] != h.getMarker()){
            System.out.println("where did the human go?");
            index = 0;
            index2 = 0;
            h.setMarker(l[0][0]);
        }
        return new IndexPair(index, index2);
    }

    public IndexPairChest getChestIndex(){
        char[][] l = fill;
        int index = 0;
        int index2 = 0;

        for(int c = 0; c < l.length; c++){
            for(int r = 0; r < l.length; r++){
                if(l[c][r] == chest.getMarker()) {
                    index = c;
                    index2 = r;
                }
            }
        }

        return new IndexPairChest(index, index2);
    }

    public IndexPairGob getGobIndex(){
        char[][] l = fill;
        int index = 0;
        int index2 = 0;
        for(int col = 0; col < l.length; col++){

            for(int row = 0; row < l.length; row++){
                if(l[col][row] == g.getMarker()){
                    index = col;
                    index2 = row;
                }
            }
        }

        if(l[index][index2] != g.getMarker()){
            System.out.println("where did the goblin go?");
            initChest();
            battle(l);
            index = size;
            index2 = size;
        }
        return new IndexPairGob(index, index2);
    }

    public char[][] battle(char[][] x){
        char[][] l = x;

        System.out.println("You encountered a goblin! You take out your " + h.getWep() +".");

        while(g.getHealth() > 0 && h.getHealth() > 0) {
            int miss = ThreadLocalRandom.current().nextInt(0, 2);
            if (miss == 1) {
                h.setHealth(h.getHealth()-g.getStrength());
                System.out.println("You took some damage... you have " + h.getHealth() + " HP left");
                g.setHealth(g.getHealth()-h.getStrength());
                System.out.println("You struck back - the goblin has " + g.getHealth() + " HP left");
            } else {
                System.out.println("The goblin missed! You take the opportunity to strike back.");
                g.setHealth(g.getHealth()-h.getStrength());
                System.out.println("The goblin has " + g.getHealth() + " HP left");
            }
        }
        if(h.getHealth() >= 1 && g.getHealth() <= 0){
            System.out.println("You took em out, good job!");
            if(p.getRoll() == 5){
                h.setHealth(h.getHealth() + p.getHealth());
                System.out.println("You've found a potion off the goblin and healed for " + p.getHealth() + " HP!");
            }
            g.setHealth(30);

            if(battles % 3 == 0) {
                initChest();
                System.out.println("A chest has spawned somewhere, go find it!");//spawn goblin and chest
            }

            battles++;
            int hc = getHumIndex().getFirst();
            int hr = getHumIndex().getSecond();
            if(l[hc][hr] == l[size][size] || hc+2 + hr+2 >= size + size){
                l[hc][hr] = '-';
                l[0][0] = h.getMarker();
                l[size][size] = g.getMarker();
                System.out.println("Spawning you to safety..."); //make sure human marker respawns starting point
            }else{
                l[hc][hr] = h.getMarker();//make sure human marker takes prio for winning
                l[size][size] = g.getMarker();
            }
        }if(h.getHealth() <= 0){
            System.out.println("You died to a goblin...? Game Over.");
            try {
                exit(0);
            }catch (Exception e){
                exit(0);
            }
        }

        return l;
    }

    public char[][] move(Character coord, char[][] x){
        Controller controller = new Controller(); //handling input
        char[][] l = x;
        int c = getHumIndex().getFirst();
        int r = getHumIndex().getSecond();
        int c1 = getChestIndex().getFirst();
        int c2 = getChestIndex().getSecond();

        try {
            switch (coord) { //move location to -
                case 'n':
                    l[c - 1][r] = h.getMarker();
                    if(battles >= 1 && c1 == c-1 && c2 == r){
                        h.chestLoot();
                    }
                    break;
                case 's':
                    l[c + 1][r] = h.getMarker();
                    if(battles >= 1 && c1 == c+1 && c2 == r){
                        h.chestLoot();
                    }
                    break;
                case 'e':
                    l[c][r + 1] = h.getMarker();
                    if(battles >= 1 && c1 == c && c2 == r+1){
                        h.chestLoot();
                    }
                    break;
                case 'w':
                    l[c][r - 1] = h.getMarker();
                    if(battles >= 1 && c1 == c && c2 == r-1){
                        h.chestLoot();
                    }
                    break;
                default:
                    System.out.println("error");
                    System.out.println("\nYou can't move off the grid! Try again.");
                    controller.setConsole("");
                    String input = controller.getConsole().getText();
                    move(input.charAt(0), l);
            }
        } catch (Exception e) {
            System.out.println("\nYou can't move off the grid! Try again.");
            controller.setConsole("");
            String input = controller.getConsole().getText();
            move(input.charAt(0), l);
        }

        l[c][r] = '-'; //remove current location
        return l;
    }

    public char[][] chase(char[][] x){
        char[][]l = x;
        int c = getGobIndex().getFirst();
        int r = getGobIndex().getSecond();
        int fC = getHumIndex().getFirst();
        int fR = getHumIndex().getFirst();
        int range = ThreadLocalRandom.current().nextInt(0, 2);
        l[c][r] = '-'; //assign current location to empty in prep for move
        try{
            if(c == fC && r == fR || (c+range == fC && r+range == fR) || (c-range == fC && r-range == fR)){
                battle(l);
            }
            else if(c < fC && r < fR ){ //coordinates of goblin < coordinates of human
                l[c + range][r + range] = g.getMarker();
            }
            else if(c > fC && r > fR){ //coordinates of goblin > coordinates of human
                l[c - range][r - range] = g.getMarker();
            }
        }catch (Exception e){
            l[c][r] = g.getMarker();
            l[fC][fR] = h.getMarker();
        }
        return l;
    }
}
