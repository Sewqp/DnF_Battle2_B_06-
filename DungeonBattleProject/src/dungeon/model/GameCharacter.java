package dungeon.model;

public abstract class GameCharacter {
    private String name;
    private String job;
    private int level;
    private int hp;
    private int attackPower;
    private Inventory inventory;
    private Guild guild;

    public GameCharacter(String name, String job, int level, int hp, int attackPower) {
        this.name = name;
        this.job = job;
        this.level = level;
        this.hp = hp;
        this.attackPower = attackPower;
        this.inventory = new Inventory();
    }

    public abstract int useSkill();

    public abstract String getSkillName();

    public void addItem(String itemName, String itemType, int itemValue) {
        Item item = new Item(itemName, itemType, itemValue);
        inventory.addItem(item);
    }

    public void showInventory() {
        inventory.showItems();
    }

    public void joinGuild(Guild guild) {
        if (this.guild != null) {
            System.out.println("이미 길드에 가입되어 있습니다.");
            return;
        }

        boolean result = guild.join(this);

        if (result) {
            this.guild = guild;
        }
    }

    public String getName() {
        return name;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public String getInfo() {
        return "캐릭터명: " + name +
                "\n직업: " + job +
                "\n레벨: " + level +
                "\nHP: " + hp +
                "\n공격력: " + attackPower;
    }
}