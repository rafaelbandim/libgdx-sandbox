package com.rafaelbandim.service.impl;

import com.esotericsoftware.kryonet.Connection;
import com.rafaelbandim.network.GameResponse;
import com.rafaelbandim.server.GameServer;
import com.rafaelbandim.service.RequestProcessor;

abstract class RequestProcessorImpl implements RequestProcessor {
    private final GameServer gameServer;

    public RequestProcessorImpl(GameServer gameServer) {
        this.gameServer = gameServer;
    }

    @Override
    public void sendTCP(Connection connection, GameResponse gameResponse) {
        gameServer.sendTCP(connection, gameResponse);
    }
}
