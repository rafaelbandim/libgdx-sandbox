package com.rafaelbandim.network.kryonet;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.rafaelbandim.network.BodyType;
import com.rafaelbandim.network.GameResponse;
import com.rafaelbandim.service.ServiceRegistry;
import org.springframework.stereotype.Component;


@Component
class ResponseListenerImpl extends Listener {
    private ServiceRegistry serviceRegistry;

    public ResponseListenerImpl(ServiceRegistry serviceRegistry) {
        this.serviceRegistry = serviceRegistry;
    }


    @Override
    public void received(Connection connection, Object object) {
        if (object instanceof GameResponse) {
            GameResponse gameResponse = (GameResponse) object;
            serviceRegistry.get(BodyType.valueOf(gameResponse.getBodyType().toString())).process(gameResponse);
        }
    }
}
