package com.rafaelbandim.dto;

import java.util.ArrayList;
import java.util.List;

public class PlayersDTO {
    List<PlayerDTO> players;

    public PlayersDTO() {
        this.players = new ArrayList<>();
    }

    public PlayersDTO(List<PlayerDTO> players) {
        this.players = players;
    }

    public List<PlayerDTO> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerDTO> players) {
        this.players = players;
    }
}
