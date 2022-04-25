import java.util.concurrent.ThreadLocalRandom;

public class Chest {
    Sword sword = new Sword();
    private Character marker = 'O';
    private int roll = 0;

    public Character getMarker() {
        return marker;
    }

    public void setMarker(Character marker) {
        this.marker = marker;
    }

    public int getRoll() {
        roll = ThreadLocalRandom.current().nextInt(1,5);
        return roll;
    }
}
