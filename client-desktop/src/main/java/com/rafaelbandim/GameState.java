package com.rafaelbandim;

import com.rafaelbandim.gameobjects.Player;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class GameState {
    private final Map<String, Player> players;
    private String myUuid;

    public GameState() {
        players = new HashMap();
    }

    public Player getPlayer(String uuid) {
        return players.get(uuid);
    }
    public Player getPlayer(){
        return getPlayer(myUuid);
    }

    public void addPlayer(Player player) {
        players.put(player.getUuid(),player);
    }

    public void addMyPlayer(Player player) {
        myUuid = player.getUuid();
        addPlayer(player);
    }

    public Collection<Player> getAllPlayers() {
        return players.values();
    }

}
