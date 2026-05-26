package dnf;
public class Mage extends Character {
    public Mage(String charName, int level) {
        super(charName, "마법사", level);
        this.hp     = level * 60;
        this.attack = level * 25;
    }
    @Override
    public double useSkill() { return attack * 2.0; }
}
