package com.rafaelbandim;

import com.rafaelbandim.server.GameServer;
import com.rafaelbandim.server.kryonet.ServiceRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class ServerApplicationBoot {
    private static final Logger LOG = LogManager.getLogger(ServerApplicationBoot.class);
    public static void main(String[] args) throws IOException {
        LOG.info("Starting Game server");
        ApplicationContext context = new AnnotationConfigApplicationContext("com.rafaelbandim");
        GameServer gameServer = context.getBean(GameServer.class);
        ServiceRegistry serviceRegistry = context.getBean(ServiceRegistry.class);
        LOG.info("Loading services");
        serviceRegistry.loadServices();
        LOG.info("Services loaded");
        gameServer.run();
        LOG.info("Server is running");
    }


}
