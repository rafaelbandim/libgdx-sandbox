package com.rafaelbandim.service;

import com.badlogic.gdx.math.Vector2;
import com.rafaelbandim.GameState;
import com.rafaelbandim.dto.PlayerDTO;
import com.rafaelbandim.gameobjects.Player;
import com.rafaelbandim.network.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class LoginService extends GenericRequest implements ResponseProcessor {
    private final Logger LOG = LogManager.getLogger(LoginService.class);
    private final GameState gameState;
    private final AtlasService atlasService;

    public LoginService(Network network, GameState gameState, AtlasService atlasService) {
        super(network);
        this.gameState = gameState;
        this.atlasService = atlasService;
    }

    @Override
    public void process(GameResponse gameResponse) {
        if (Status.SUCCESS.equals(gameResponse.getStatus())) {
            PlayerDTO playerDTO = (PlayerDTO) gameResponse.getBody();
            LOG.info("My uuid: {}", playerDTO.getUuid());
            Player player = new Player(atlasService.getPlayerAtlas(), new Vector2(playerDTO.getPosition().getX(), playerDTO.getPosition().getY()), playerDTO.getUuid());
            this.gameState.addMyPlayer(player);
        } else {
            throw new RuntimeException("Something went wrong");
        }
    }

    public void login() {
        getNetwork().addRequest(new GameRequest(null, BodyType.LOGIN));
    }
}
