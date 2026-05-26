package dungeon.model;

public class Player {
    private String playerId;

    public Player(String playerId) {
        this.playerId = playerId;
    }

    public boolean checkPlayer() {
        return playerId.equals("Hero");
    }

    public String getPlayerId() {
        return playerId;
    }
}