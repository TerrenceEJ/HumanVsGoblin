import java.util.concurrent.ThreadLocalRandom;

public class Potion {
    private int health = 20;
    private int roll = 0;

    public int getHealth() {
        return health;
    }

    public int getRoll() {
        roll = ThreadLocalRandom.current().nextInt(1,10);
        return roll;
    }
}
