package com.rafaelbandim.service.impl;

import com.esotericsoftware.kryonet.Connection;
import com.rafaelbandim.cache.GameState;
import com.rafaelbandim.dto.PlayerDTO;
import com.rafaelbandim.dto.Vector2DTO;
import com.rafaelbandim.network.BodyType;
import com.rafaelbandim.network.GameRequest;
import com.rafaelbandim.network.GameResponse;
import com.rafaelbandim.network.Status;
import com.rafaelbandim.server.GameServer;
import com.rafaelbandim.service.LoginService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
class LoginServiceImpl extends RequestProcessorImpl implements LoginService {
    private final Logger LOG = LogManager.getLogger(LoginServiceImpl.class);

    private final GameState gameState;

    public LoginServiceImpl(GameState gameState, GameServer gameServer) {
        super(gameServer);
        this.gameState = gameState;
    }

    @Override
    public void process(Connection connection, GameRequest gameRequest) {
        LOG.info("Login requested");
        GameResponse gameResponse = new GameResponse(null, BodyType.LOGIN, Status.FAIL);
        PlayerDTO playerDTO = new PlayerDTO(new Vector2DTO(0, 0), UUID.randomUUID().toString(), 1);
        gameState.addPlayer(playerDTO);
        gameResponse.setStatus(Status.SUCCESS);
        gameResponse.setBody(playerDTO);
        sendTCP(connection, gameResponse);
        LOG.info("Login requested: success");
    }

}