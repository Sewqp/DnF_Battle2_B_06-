package dungeon.model;

public class Item {
    private String itemName;
    private String itemType;
    private int itemValue;

    public Item(String itemName, String itemType, int itemValue) {
        this.itemName = itemName;
        this.itemType = itemType;
        this.itemValue = itemValue;
    }

    public boolean isValidType() {
        return itemType.equals("무기") || itemType.equals("방어구") || itemType.equals("물약");
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemType() {
        return itemType;
    }

    public int getItemValue() {
        return itemValue;
    }

    public String getItemGrade() {
        if (itemValue >= 1000) {
            return "전설";
        } else if (itemValue >= 500) {
            return "희귀";
        } else {
            return "일반";
        }
    }
}