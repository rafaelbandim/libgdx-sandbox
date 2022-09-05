package com.rafaelbandim.server.kryonet;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Server;
import com.rafaelbandim.dto.PayloadClasses;
import com.rafaelbandim.server.GameServer;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
class KryonetServerImpl implements GameServer {

    private final RequestListenerImpl requestListener;
    private final Server server;

    public KryonetServerImpl(RequestListenerImpl requestListener) {
        this.requestListener = requestListener;
        server = new Server();

    }

    @Override
    public void run() throws IOException {
        registerRequestClasses();
        server.start();
        server.bind(54555, 54777);
        server.addListener(requestListener);
    }

    public void sendTCP(Connection connection, Object object) {
        server.sendToTCP(connection.getID(), object);
    }

    private void registerRequestClasses() {
        Kryo kryo = server.getKryo();
        for (Class<?> clazz : PayloadClasses.getClasses()) {
            kryo.register(clazz);
        }
    }
}
