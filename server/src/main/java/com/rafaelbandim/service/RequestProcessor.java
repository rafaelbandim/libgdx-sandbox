package com.rafaelbandim.service;

import com.esotericsoftware.kryonet.Connection;
import com.rafaelbandim.network.GameRequest;
import com.rafaelbandim.network.GameResponse;

public interface RequestProcessor {
    void process(Connection connection, GameRequest gameRequest);

    void sendTCP(Connection connection, GameResponse gameResponse);

}
