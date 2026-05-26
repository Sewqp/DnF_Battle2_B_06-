package dnf;
public class Warrior extends Character {
    public Warrior(String charName, int level) {
        super(charName, "전사", level);
        this.hp     = level * 100;
        this.attack = level * 15;
    }
    @Override
    public double useSkill() { return attack * 1.5; }
}
