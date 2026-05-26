package dungeon.model;

public class Mage extends GameCharacter {
    public Mage(String name, int level) {
        super(name, "마법사", level, level * 60, level * 25);
    }

    public int useSkill() {
        return (int)(getAttackPower() * 2.0);
    }

    public String getSkillName() {
        return "파이어볼";
    }
}