package dungeon.model;

public class Monster {
    private int hp;

    public Monster(int hp) {
        this.hp = hp;
    }

    public void takeDamage(int damage) {
        hp -= damage;

        if (hp < 0) {
            hp = 0;
        }
    }

    public boolean isDead() {
        return hp <= 0;
    }

    public int getHp() {
        return hp;
    }
}