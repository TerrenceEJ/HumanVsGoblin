import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Land land = new Land();
        int turn = 1;

        System.out.println("Would you like to start Goblins vs Humans? y/n");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if(input.charAt(0) == 'y'){
            System.out.println(land);
            while(turn > 0) { //inf loot. current plan is to let player play as long as they can

                System.out.println("\nNow that you've seen the map layout, where would you like to move? n/s/e/w");
                input = scanner.nextLine();

                land.move(input.charAt(0), land.fill);
                land.chase(land.fill);
                System.out.println(land);
                turn++;
            }

        }else{
            System.out.println(land + "\nEnding game.");
        }


    }
}
