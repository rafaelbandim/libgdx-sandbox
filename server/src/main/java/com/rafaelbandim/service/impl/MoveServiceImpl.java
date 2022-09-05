package com.rafaelbandim.service.impl;

import com.badlogic.gdx.Input;
import com.esotericsoftware.kryonet.Connection;
import com.rafaelbandim.cache.GameState;
import com.rafaelbandim.dto.MoveInputDTO;
import com.rafaelbandim.dto.PlayerDTO;
import com.rafaelbandim.network.BodyType;
import com.rafaelbandim.network.GameRequest;
import com.rafaelbandim.network.GameResponse;
import com.rafaelbandim.network.Status;
import com.rafaelbandim.server.GameServer;
import com.rafaelbandim.service.MoveService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;


@Service
public class MoveServiceImpl extends RequestProcessorImpl implements MoveService {
    private final Logger LOG = LogManager.getLogger(MoveServiceImpl.class);
    private final GameState gameState;

    public MoveServiceImpl(GameServer gameServer, GameState gameState) {
        super(gameServer);
        this.gameState = gameState;
    }

    @Override
    public void process(Connection connection, GameRequest gameRequest) {
        LOG.debug("Move request received");
        MoveInputDTO moveInputDTO = (MoveInputDTO) gameRequest.getBody();
        PlayerDTO playerDTO = gameState.get(moveInputDTO.getUuid());
        Integer key = moveInputDTO.getDirection();
        if (Input.Keys.UP == key) {
            playerDTO.getPosition().setX(playerDTO.getPosition().getX());
            playerDTO.getPosition().setY(playerDTO.getPosition().getY() + playerDTO.getSpeed());
        } else if (Input.Keys.DOWN == key) {
            playerDTO.getPosition().setX(playerDTO.getPosition().getX());
            playerDTO.getPosition().setY(playerDTO.getPosition().getY() - playerDTO.getSpeed());
        } else if (Input.Keys.RIGHT == key) {
            playerDTO.getPosition().setX(playerDTO.getPosition().getX() + playerDTO.getSpeed());
            playerDTO.getPosition().setY(playerDTO.getPosition().getY());
        } else if (Input.Keys.LEFT == key) {
            playerDTO.getPosition().setX(playerDTO.getPosition().getX() - playerDTO.getSpeed());
            playerDTO.getPosition().setY(playerDTO.getPosition().getY());
        }
        GameResponse gameResponse = new GameResponse(playerDTO, BodyType.MOVE, Status.SUCCESS);
        sendTCP(connection, gameResponse);
    }

}
