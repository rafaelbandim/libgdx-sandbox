package com.rafaelbandim;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import org.springframework.stereotype.Component;

@Component
class DesktopApplication {


    public DesktopApplication(GameApplication gameApplication) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setForegroundFPS(60);
        config.useVsync(true);
        config.setTitle("mmorpg");
        config.setWindowedMode(800, 600);
        new Lwjgl3Application(gameApplication, config);
    }
}
