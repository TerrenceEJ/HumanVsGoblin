public class Human {
    Club club = new Club();
    Sword sword = new Sword();
    Chest chest = new Chest();
    Potion p = new Potion();
    private int health = 100, strength = 2, intelligence = 0;
    private Character marker = 'H';
    private Equipment wep = club;

    public Human(){
        this.health += wep.getHealth();
        this.intelligence += wep.getIntelligence();
        this.strength += wep.getStrength();
    }

    public void setWep(Equipment wep) {
        this.wep = wep;
        this.strength += wep.getStrength();
        this.health += wep.getHealth();
        this.intelligence += wep.getIntelligence();
    }

    public void chestLoot(){
        if (chest.getRoll() == 2){
            setWep(sword);
            System.out.println("You've obtained a " + this.getWep() + "!");
        }else{
            this.health += p.getHealth();
            System.out.println("No big loot, you got a potion, though. HP + " + p.getHealth());
        }
    }

    public String getWep(){
        String clean = "";
        clean = wep.getClass().getSimpleName();
        return clean;
    }

    public Character getMarker() {
        return marker;
    }

    public void setMarker(Character marker) {
        this.marker = marker;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }
}
