package com.rafaelbandim.server.kryonet;

import com.rafaelbandim.network.BodyType;
import com.rafaelbandim.service.*;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class ServiceRegistry {

    private final ApplicationContext applicationContext;
    private final Map<BodyType, RequestProcessor> serviceMap;

    public ServiceRegistry(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        serviceMap = new HashMap<>();
    }

    public void loadServices() {
        serviceMap.put(BodyType.ECHO, applicationContext.getBean(EchoService.class));
        serviceMap.put(BodyType.LOGIN, applicationContext.getBean(LoginService.class));
        serviceMap.put(BodyType.MOVE, applicationContext.getBean(MoveService.class));
        serviceMap.put(BodyType.PLAYERS, applicationContext.getBean(PlayersService.class));
    }

    public RequestProcessor get(BodyType bodyType) {
        return serviceMap.get(bodyType);
    }
}
