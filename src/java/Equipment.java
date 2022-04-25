import java.lang.reflect.Constructor;

public interface Equipment {
    int strength = 10; int intelligence = 10; int health = 10;

    default int stats(int strength, int intelligence, int health){
        strength = Equipment.health;
        intelligence = Equipment.intelligence;
        health = Equipment.health;
        return stats(strength, intelligence, health);
    }

    default int getStrength(){
        return strength;
    }
    default int getHealth(){
        return health;
    }
    default int getIntelligence(){ return intelligence; }
}
