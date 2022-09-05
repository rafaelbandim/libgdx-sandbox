package com.rafaelbandim.service;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.rafaelbandim.GameState;
import com.rafaelbandim.dto.PlayerDTO;
import com.rafaelbandim.dto.PlayersDTO;
import com.rafaelbandim.gameobjects.Player;
import com.rafaelbandim.network.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class PlayersService extends GenericRequest implements ResponseProcessor {
    private final Logger LOG = LogManager.getLogger(PlayersService.class);
    private final GameState gameState;
    private final AtlasService atlasService;

    public PlayersService(Network network, GameState gameState, AtlasService atlasService) {
        super(network);
        this.gameState = gameState;
        this.atlasService = atlasService;
    }

    public void updateGameObjects() {
        getNetwork().addRequest(new GameRequest(null, BodyType.PLAYERS));
    }

    @Override
    public void process(GameResponse gameResponse) {
        if (Status.SUCCESS.equals(gameResponse.getStatus())) {
            PlayersDTO playersDTO = (PlayersDTO) gameResponse.getBody();
            playersDTO.getPlayers().stream().forEach(this::updateGameState);
        } else {
            throw new RuntimeException("Something went wrong");
        }
    }

    private void updateGameState(PlayerDTO playerDTO) {
        Player player = gameState.getPlayer(playerDTO.getUuid());
        Vector2 newPosition = new Vector2(playerDTO.getPosition().getX(), playerDTO.getPosition().getY());
        if (player != null) {
            float horizontalDifference = player.getPosition().x - playerDTO.getPosition().getX();
            float verticalDifference = player.getPosition().y - playerDTO.getPosition().getY();
            if (Math.abs(horizontalDifference) > Math.abs(verticalDifference)) {
                if (horizontalDifference > 0) {
                    player.setAnimation(Input.Keys.LEFT);
                } else {
                    player.setAnimation(Input.Keys.RIGHT);
                }
            } else if (Math.abs(horizontalDifference) < Math.abs(verticalDifference)) {
                if (verticalDifference > 0) {
                    player.setAnimation(Input.Keys.DOWN);
                } else {
                    player.setAnimation(Input.Keys.UP);
                }
            }
            player.setPosition(newPosition);
        } else {
            player = new Player(atlasService.getPlayerAtlas(), newPosition, playerDTO.getUuid());
            gameState.addPlayer(player);
        }
    }
}
