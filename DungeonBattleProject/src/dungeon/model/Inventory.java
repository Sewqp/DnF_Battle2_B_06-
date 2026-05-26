package dungeon.model;

import java.util.ArrayList;

public class Inventory {
    private ArrayList<Item> items;

    public Inventory() {
        items = new ArrayList<Item>();
    }

    public void addItem(Item item) {
        if (!item.isValidType()) {
            System.out.println("아이템 타입은 무기, 방어구, 물약만 입력할 수 있습니다.");
            return;
        }

        if (items.size() >= 10) {
            System.out.println("인벤토리가 가득 찼습니다.");
            return;
        }

        items.add(item);
        System.out.println(item.getItemName() + " 아이템을 획득했습니다.");
    }	

    public void showItems() {
        if (items.size() == 0) {
            System.out.println("보유 아이템이 없습니다.");
            return;
        }

        for (Item item : items) {
            System.out.println("아이템명: " + item.getItemName()
                    + ", 타입: " + item.getItemType()
                    + ", 가치: " + item.getItemValue()
                    + ", 등급: " + item.getItemGrade());
        }
    }
}