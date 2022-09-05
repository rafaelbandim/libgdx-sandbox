package com.rafaelbandim.service;

import com.rafaelbandim.network.BodyType;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ServiceRegistry {

    private final ApplicationContext applicationContext;
    private final Map<BodyType, ResponseProcessor> serviceMap;

    public ServiceRegistry(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        serviceMap = new HashMap<>();
    }

    public void loadServices() {
        serviceMap.put(BodyType.LOGIN, applicationContext.getBean(LoginService.class));
        serviceMap.put(BodyType.MOVE, applicationContext.getBean(MoveService.class));
        serviceMap.put(BodyType.PLAYERS, applicationContext.getBean(PlayersService.class));
    }

    public ResponseProcessor get(BodyType bodyType) {
        return serviceMap.get(bodyType);
    }
}
