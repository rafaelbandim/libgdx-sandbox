package com.rafaelbandim.network.kryonet;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.rafaelbandim.dto.PayloadClasses;
import com.rafaelbandim.network.GameRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class KryoClient {
    private final Client client;

    public KryoClient(ResponseListenerImpl responseListener) throws IOException {
        client = new Client();
        registerRequestClasses(client.getKryo());
        client.start();
        client.connect(5000, "ec2-35-90-166-71.us-west-2.compute.amazonaws.com", 54555, 54777);
        //client.connect(5000, "localhost", 54555, 54777);
        client.addListener(responseListener);
    }

    public void sendTCP(GameRequest gameRequest) {
        client.sendTCP(gameRequest);
    }

    private void registerRequestClasses(Kryo kryo) {
        for (Class<?> clazz : PayloadClasses.getClasses()) {
            kryo.register(clazz);
        }
    }

}
