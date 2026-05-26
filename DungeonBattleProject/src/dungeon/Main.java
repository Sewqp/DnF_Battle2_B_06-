package dungeon;

import java.util.Scanner;

import dungeon.model.GameCharacter;
import dungeon.model.Guild;
import dungeon.model.Mage;
import dungeon.model.Monster;
import dungeon.model.Player;
import dungeon.model.Warrior;
import dungeon.service.Battle;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("===== 던전앤파이터 전투 시스템 =====");

        System.out.print("Player ID 입력: ");
        String playerId = sc.nextLine();

        Player player = new Player(playerId);

        if (!player.checkPlayer()) {
            System.out.println("플레이어 체크 실패");
            System.out.println("Player ID가 Hero일 때만 실행할 수 있습니다.");
            sc.close();
            return;
        }

        System.out.print("캐릭터명 입력: ");
        String name = sc.nextLine();

        System.out.println("직업 선택");
        System.out.println("1. 전사");
        System.out.println("2. 마법사");
        System.out.print("번호 입력: ");
        int jobChoice = sc.nextInt();

        System.out.print("레벨 입력: ");
        int level = sc.nextInt();

        System.out.print("몬스터 HP 입력: ");
        int monsterHp = sc.nextInt();

        if (level <= 0 || monsterHp <= 0) {
            System.out.println("레벨과 몬스터 HP는 1 이상이어야 합니다.");
            sc.close();
            return;
        }

        GameCharacter character;

        if (jobChoice == 1) {
            character = new Warrior(name, level);
        } else if (jobChoice == 2) {
            character = new Mage(name, level);
        } else {
            System.out.println("잘못된 직업 선택입니다.");
            sc.close();
            return;
        }

        Monster monster = new Monster(monsterHp);
        Battle battle = new Battle(character, monster);

        System.out.println();
        System.out.println("캐릭터 생성 완료");
        System.out.println(character.getInfo());
        System.out.println("몬스터 HP: " + monster.getHp());

        Guild guild = new Guild("드래곤 길드");

        while (!monster.isDead()) {
            System.out.println();
            System.out.println("1. 몬스터 공격");
            System.out.println("2. 아이템 획득");
            System.out.println("3. 인벤토리 확인");
            System.out.println("4. 길드 가입");
            System.out.println("5. 종료");
            System.out.print("선택: ");
            int menu = sc.nextInt();

            if (menu == 1) {
                if (!player.checkPlayer()) {
                    System.out.println("플레이어 체크 실패");
                    continue;
                }

                battle.attackMonster();

            } else if (menu == 2) {
                if (!player.checkPlayer()) {
                    System.out.println("플레이어 체크 실패");
                    continue;
                }

                sc.nextLine();

                System.out.print("아이템명 입력: ");
                String itemName = sc.nextLine();

                System.out.print("아이템 타입 입력: ");
                String itemType = sc.nextLine();

                System.out.print("아이템 가치 입력: ");

                if (!sc.hasNextInt()) {
                    System.out.println("아이템 가치는 숫자로 입력해야 합니다.");
                    sc.nextLine();
                    continue;
                }

                int itemValue = sc.nextInt();
                battle.addItem(player, character, itemName, itemType, itemValue);

            } else if (menu == 3) {
                character.showInventory();

            } else if (menu == 4) {
                if (!player.checkPlayer()) {
                    System.out.println("플레이어 체크 실패");
                    continue;
                }

                battle.joinGuild(player, character, guild);

            } else if (menu == 5) {
                System.out.println("전투를 종료합니다.");
                break;
            } else {
                System.out.println("잘못된 입력입니다.");
            }
        }

        sc.close();
    }
}