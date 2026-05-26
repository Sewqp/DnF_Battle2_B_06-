package dungeon.model;

import java.util.ArrayList;

public class Guild {
    private String guildName;
    private ArrayList<GameCharacter> members;
    private int maxMember;

    public Guild(String guildName) {
        this.guildName = guildName;
        this.members = new ArrayList<GameCharacter>();
        this.maxMember = 5;
    }

    public boolean join(GameCharacter character) {
        if (members.size() >= maxMember) {
            System.out.println("길드 정원이 가득 차서 가입할 수 없습니다.");
            return false;
        }

        members.add(character);
        System.out.println(character.getName() + " 캐릭터가 " + guildName + " 길드에 가입했습니다.");
        return true;
    }

    public String getGuildName() {
        return guildName;
    }
}