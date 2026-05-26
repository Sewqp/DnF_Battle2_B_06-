package dnf;

import java.util.ArrayList;
import java.util.List;

public class Inventory implements java.io.Serializable {
    private List<Item> 아이템리스트;
    private int 최대용량;

    public Inventory() {
        this.아이템리스트 = new ArrayList<>();
        this.최대용량    = 10;
    }

    public boolean 아이템추가(Item item) {
        if (아이템리스트.size() >= 최대용량) return false;
        아이템리스트.add(item);
        return true;
    }

    public List<Item> get아이템리스트() { return 아이템리스트; }
    public int get최대용량()            { return 최대용량; }
    public int get현재수량()            { return 아이템리스트.size(); }
    public boolean isFull()             { return 아이템리스트.size() >= 최대용량; }
}
