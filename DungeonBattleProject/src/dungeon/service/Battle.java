package dungeon.service;

import dungeon.model.GameCharacter;
import dungeon.model.Monster;

public class Battle {
    private GameCharacter character;
    private Monster monster;

    public Battle(GameCharacter character, Monster monster) {
        this.character = character;
        this.monster = monster;
    }

    public void attackMonster() {
        int damage = character.useSkill();
        monster.takeDamage(damage);

        System.out.println();
        System.out.println("몬스터 공격 실행");
        System.out.println("스킬명: " + character.getSkillName());
        System.out.println("데미지: " + damage);
        System.out.println("데미지 등급: " + getDamageGrade(damage));
        System.out.println("남은 몬스터 HP: " + monster.getHp());

        if (monster.isDead()) {
            System.out.println("몬스터를 처치했습니다.");
        } else {
            System.out.println("몬스터가 아직 살아있습니다.");
        }
    }

    public String getDamageGrade(int damage) {
        if (damage >= 200) {
            return "S급";
        } else if (damage >= 100) {
            return "A급";
        } else {
            return "B급";
        }
    }
}