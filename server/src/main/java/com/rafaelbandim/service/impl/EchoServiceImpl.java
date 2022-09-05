package com.rafaelbandim.service.impl;

import com.esotericsoftware.kryonet.Connection;
import com.rafaelbandim.network.GameRequest;
import com.rafaelbandim.server.GameServer;
import com.rafaelbandim.service.EchoService;
import org.springframework.stereotype.Service;

@Service
class EchoServiceImpl extends RequestProcessorImpl implements EchoService {
    public EchoServiceImpl(GameServer gameServer) {
        super(gameServer);
    }

    @Override
    public void process(Connection connection, GameRequest gameRequest) {

    }
}
