package dungeon.model;

public class Warrior extends GameCharacter {
    public Warrior(String name, int level) {
        super(name, "전사", level, level * 100, level * 15);
    }

    public int useSkill() {
        return (int)(getAttackPower() * 1.5);
    }

    public String getSkillName() {
        return "검 휘두르기";
    }
}