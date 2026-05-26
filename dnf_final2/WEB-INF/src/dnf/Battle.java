package dnf;

public class Battle {
    private Player player = new Player("hero");

    // ── 캐릭터 생성 ──────────────────────────────────────────────
    public Character createCharacter(String playerId, String charName, String job, int level) {
        if (!player.checkPlayer(playerId)) return null;
        if ("전사".equals(job))   return new Warrior(charName, level);
        if ("마법사".equals(job)) return new Mage(charName, level);
        return null;
    }

    // ── 몬스터 공격 ──────────────────────────────────────────────
    public String attackMonster(String playerId, Character c, Dragon dragon) {
        if (!player.checkPlayer(playerId)) return "몬스터공격 실패: 플레이어 인증 오류";
        if (!dragon.isAlive()) return "이미 용을 처치했습니다!";

        double damage = c.useSkill();
        String skill  = (c instanceof Warrior) ? "검 휘두르기"
                      : (c instanceof Mage)    ? "파이어볼" : "기본공격";
        String grade  = judgeGrade(damage);

        dragon.takeDamage((int) damage);

        String result = skill + " | 데미지: " + (int) damage + " | " + grade;
        if (!dragon.isAlive()) result += " | ★ 용을 처치했습니다! 클리어! ★";
        return result;
    }

    public String judgeGrade(double damage) {
        if (damage >= 200) return "S급 공격";
        if (damage >= 100) return "A급 공격";
        return "B급 공격";
    }

    // ── 아이템 획득 (신규 기능 1) ─────────────────────────────────
    public String addItem(String playerId, Character c,
                          String 아이템명, String 타입, int 가치) {
        if (!player.checkPlayer(playerId)) return "아이템획득 실패: 플레이어 인증 오류";

        Inventory inv = c.get인벤토리();
        if (inv.isFull()) return "아이템획득 실패: 인벤토리가 가득 찼습니다 (최대 10칸)";

        Item item = new Item(아이템명, 타입, 가치);
        inv.아이템추가(item);
        return "아이템 획득 성공! " + item.toString()
             + " | 인벤토리: " + inv.get현재수량() + "/" + inv.get최대용량();
    }

    // ── 길드 가입 (신규 기능 2) ───────────────────────────────────
    public String joinGuild(String playerId, Character c, Guild guild) {
        if (!player.checkPlayer(playerId)) return "길드가입 실패: 플레이어 인증 오류";
        if (guild.isFull()) return "길드가입 실패: 길드 정원이 가득 찼습니다 (최대 5명)";

        guild.캐릭터가입(c); // Aggregation: 참조만 추가
        return "길드 가입 성공! [" + guild.get길드명() + "] "
             + c.getCharName() + " 가입 완료 | 길드원: "
             + guild.get현재인원() + "/" + guild.get최대인원();
    }
}
