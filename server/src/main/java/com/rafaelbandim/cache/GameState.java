package com.rafaelbandim.cache;

import com.rafaelbandim.dto.PlayerDTO;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class GameState {

    private Map<String, PlayerDTO> playerMap;

    public GameState() {
        playerMap = new HashMap<>();
    }

    public Collection<PlayerDTO> getPlayerDTOList() {
        return playerMap.values();
    }

    public PlayerDTO get(String key) {
        return playerMap.get(key);
    }

    public void addPlayer(PlayerDTO player) {
        playerMap.put(player.getUuid(), player);
    }
}
