package com.rafaelbandim.service.impl;

import com.esotericsoftware.kryonet.Connection;
import com.rafaelbandim.cache.GameState;
import com.rafaelbandim.dto.PlayerDTO;
import com.rafaelbandim.dto.PlayersDTO;
import com.rafaelbandim.network.BodyType;
import com.rafaelbandim.network.GameRequest;
import com.rafaelbandim.network.GameResponse;
import com.rafaelbandim.network.Status;
import com.rafaelbandim.server.GameServer;
import com.rafaelbandim.service.PlayersService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayersServiceImpl extends RequestProcessorImpl implements PlayersService {
    private final Logger LOG = LogManager.getLogger(PlayersServiceImpl.class);
    private final GameState gameState;

    public PlayersServiceImpl(GameServer gameServer, GameState gameState) {
        super(gameServer);
        this.gameState = gameState;
    }

    @Override
    public void process(Connection connection, GameRequest gameRequest) {
        LOG.info("Players requested");
        List<PlayerDTO> players = this.gameState.getPlayerDTOList().stream().collect(Collectors.toList());
        LOG.info("Total players after filter: {}", players.size());
        GameResponse gameResponse = new GameResponse(new PlayersDTO(players), BodyType.PLAYERS, Status.SUCCESS);
        sendTCP(connection, gameResponse);
    }
}
