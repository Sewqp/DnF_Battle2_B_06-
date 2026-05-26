package dnf;
public class Dragon implements java.io.Serializable {
    private String name = "붉은 용";
    private int maxHp   = 1000;
    private int currentHp = 1000;

    public String getName()     { return name; }
    public int getMaxHp()       { return maxHp; }
    public int getCurrentHp()   { return currentHp; }
    public boolean isAlive()    { return currentHp > 0; }

    public void takeDamage(int damage) {
        currentHp -= damage;
        if (currentHp < 0) currentHp = 0;
    }

    public int getHpPercent() {
        return (int)((currentHp / (double) maxHp) * 100);
    }
}
