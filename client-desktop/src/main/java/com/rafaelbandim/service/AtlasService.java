package com.rafaelbandim.service;


import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import org.springframework.stereotype.Service;

@Service
public class AtlasService {
    private final TextureAtlas playerAtlas;

    public AtlasService() {
        playerAtlas = new TextureAtlas("player/Player.atlas");
    }

    public TextureAtlas getPlayerAtlas() {
        return playerAtlas;
    }
}
