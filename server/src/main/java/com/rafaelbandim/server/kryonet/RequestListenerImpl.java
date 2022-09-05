package com.rafaelbandim.server.kryonet;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.rafaelbandim.network.BodyType;
import com.rafaelbandim.network.GameRequest;
import org.springframework.stereotype.Component;


@Component
class RequestListenerImpl extends Listener {
    private ServiceRegistry serviceRegistry;

    public RequestListenerImpl(ServiceRegistry serviceRegistry) {
        this.serviceRegistry = serviceRegistry;
    }

    @Override
    public void received(Connection connection, Object object) {
        if (object instanceof GameRequest) {
            GameRequest gameRequest = (GameRequest) object;
            serviceRegistry.get(BodyType.valueOf(gameRequest.getBodyType().toString())).process(connection, gameRequest);
        }
    }
}
