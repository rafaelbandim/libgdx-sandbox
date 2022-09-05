package com.rafaelbandim.server;

import com.esotericsoftware.kryonet.Connection;

import java.io.IOException;

public interface GameServer {
    void run() throws IOException;

    void sendTCP(Connection connection, Object object);
}
