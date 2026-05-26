package dnf;

public abstract class Character implements java.io.Serializable {
    protected String charName;
    protected String job;
    protected int level;
    protected int hp;
    protected double attack;

    // Composition: 캐릭터 생성 시 인벤토리 자동 생성, 캐릭터 삭제 시 함께 삭제
    private Inventory 인벤토리;

    public Character(String charName, String job, int level) {
        this.charName  = charName;
        this.job       = job;
        this.level     = level;
        this.인벤토리  = new Inventory(); // Composition: 자동 생성
    }

    public abstract double useSkill();

    public String    getCharName()  { return charName; }
    public String    getJob()       { return job; }
    public int       getLevel()     { return level; }
    public int       getHp()        { return hp; }
    public double    getAttack()    { return attack; }
    public Inventory get인벤토리() { return 인벤토리; }
}
