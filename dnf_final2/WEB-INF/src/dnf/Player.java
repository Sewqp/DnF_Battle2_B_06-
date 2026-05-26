package dnf;
public class Player {
    private String playerId;
    public Player(String playerId) { this.playerId = playerId; }
    public boolean checkPlayer(String playerId) { return "hero".equals(playerId); }
    public String getPlayerId() { return playerId; }
}
