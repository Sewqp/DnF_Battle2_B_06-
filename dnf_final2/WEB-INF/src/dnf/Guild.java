package dnf;

import java.util.ArrayList;
import java.util.List;

public class Guild implements java.io.Serializable {
    private String 길드명;
    private List<Character> 캐릭터리스트;
    private int 최대인원;

    public Guild(String 길드명) {
        this.길드명        = 길드명;
        this.캐릭터리스트  = new ArrayList<>();
        this.최대인원      = 5;
    }

    // Aggregation: 길드가 해체되어도 캐릭터는 존재 (참조만 보관)
    public boolean 캐릭터가입(Character c) {
        if (캐릭터리스트.size() >= 최대인원) return false;
        캐릭터리스트.add(c);
        return true;
    }

    public String get길드명()                  { return 길드명; }
    public List<Character> get캐릭터리스트()   { return 캐릭터리스트; }
    public int get최대인원()                   { return 최대인원; }
    public int get현재인원()                   { return 캐릭터리스트.size(); }
    public boolean isFull()                    { return 캐릭터리스트.size() >= 최대인원; }
}
