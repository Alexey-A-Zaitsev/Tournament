package Tournament;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Game {
    //List<Player> players = new ArrayList<Player>();
    private HashMap<String, Player> players = new HashMap();

    public HashMap<String, Player> getPlayers() {
        return players;
    }

    public void setPlayers(HashMap<String, Player> players) {
        this.players = players;
    }

    public void register (Player player) {
        players.put(player.getName(), player);
    }

    public int round (String playerName1, String playerName2) {
        Player player1 = null;
        Player player2 = null;

        if (players.containsKey(playerName1)) {
            player1 = players.get(playerName1);
        } else {
            throw new NotRegisteredException(playerName1);
        }

        if (players.containsKey(playerName2)) {
            player2 = players.get(playerName2);
        } else {
            throw new NotRegisteredException(playerName2);
        }

        if (player1.getStrength() > player2.getStrength()) {
            return 1;
        }
        if (player1.getStrength() < player2.getStrength()) {
            return -1;
        }
        return 0;
    }
}
