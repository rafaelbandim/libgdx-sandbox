package com.rafaelbandim;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rafaelbandim.service.ServiceRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
class GameApplication extends Game {

    private final ServiceRegistry serviceRegistry;
    private final OrthographicCamera orthographicCamera;
    private final ApplicationContext applicationContext;
    private int widthScreen, heightScreen;
    private Viewport viewport;

    public GameApplication(OrthographicCamera orthographicCamera, ApplicationContext applicationContext, ServiceRegistry serviceRegistry) {
        this.orthographicCamera = orthographicCamera;
        this.applicationContext = applicationContext;
        this.serviceRegistry = serviceRegistry;
    }

    @Override
    public void create() {
        serviceRegistry.loadServices();
        widthScreen = Gdx.graphics.getWidth();
        heightScreen = Gdx.graphics.getHeight();
        orthographicCamera.setToOrtho(false, widthScreen, heightScreen);
        viewport = new ExtendViewport(200, 200, orthographicCamera);
        setScreen(applicationContext.getBean(GameScreen.class));
    }


    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

}
