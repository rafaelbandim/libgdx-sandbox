package com.rafaelbandim.network.kryonet;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.rafaelbandim.network.BodyType;
import com.rafaelbandim.network.GameRequest;
import com.rafaelbandim.network.GameResponse;
import com.rafaelbandim.network.Network;
import com.rafaelbandim.service.ServiceRegistry;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Queue;

@Service
class NetworkImpl extends Listener implements Network {

    private final ServiceRegistry serviceRegistry;
    private final KryoClient client;
    private final Queue<GameRequest> requestQueue;
    private final Queue<GameResponse> gameResponses;


    private NetworkImpl(ServiceRegistry serviceRegistry, KryoClient kryoClient) {
        this.serviceRegistry = serviceRegistry;
        this.client = kryoClient;
        requestQueue = new LinkedList();
        gameResponses = new LinkedList();

    }

    @Override
    public void sendRequests() {
        while (requestQueue.size() > 0) {
            client.sendTCP(requestQueue.remove());
        }
    }

    @Override
    public void readResponses() {
        while (gameResponses.size() > 0) {
            GameResponse gameResponse = gameResponses.remove();
            serviceRegistry.get(BodyType.valueOf(gameResponse.getBodyType().toString())).process(gameResponse);
        }
    }

    @Override
    public void addRequest(GameRequest gameRequest) {
        requestQueue.add(gameRequest);
    }

    @Override
    public void received(Connection connection, Object object) {
        gameResponses.add((GameResponse) object);
    }
}
