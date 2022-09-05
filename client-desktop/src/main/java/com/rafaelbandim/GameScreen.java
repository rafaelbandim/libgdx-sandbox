package com.rafaelbandim;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rafaelbandim.network.Network;
import com.rafaelbandim.service.LoginService;
import com.rafaelbandim.service.MoveService;
import com.rafaelbandim.service.PlayersService;
import org.springframework.stereotype.Component;

@Component
class GameScreen extends ScreenAdapter {

    private final OrthographicCamera camera;
    private final GameState gameState;
    private final Network network;
    private SpriteBatch spriteBatch;
    private MoveService moveService;
    private PlayersService playersService;

    public GameScreen(
            LoginService loginService,
            OrthographicCamera orthographicCamera,
            Network network, GameState gameState,
            MoveService moveService,
            PlayersService playersService) {
        this.camera = orthographicCamera;
        this.network = network;
        this.gameState = gameState;
        this.moveService = moveService;
        this.playersService = playersService;
        spriteBatch = new SpriteBatch();
        loginService.login();

    }

    @Override
    public void render(float delta) {
        readPlayerInput();
        updateGameState();
        renderGameObjects();
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
    }

    private void updateGameState() {
        playersService.updateGameObjects();
        network.sendRequests();
        network.readResponses();
    }

    private void renderGameObjects() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        gameState.getAllPlayers().stream().forEach(otherPlayers -> otherPlayers.draw(spriteBatch));
        spriteBatch.end();
        camera.position.set(0, 0, 0);
        camera.update();
        spriteBatch.setProjectionMatrix(camera.combined);
    }

    private void readPlayerInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            moveService.move(Input.Keys.UP);
            this.gameState.getPlayer().setAnimation(Input.Keys.UP);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            moveService.move(Input.Keys.DOWN);
            this.gameState.getPlayer().setAnimation(Input.Keys.DOWN);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            moveService.move(Input.Keys.RIGHT);
            this.gameState.getPlayer().setAnimation(Input.Keys.RIGHT);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            moveService.move(Input.Keys.LEFT);
            this.gameState.getPlayer().setAnimation(Input.Keys.LEFT);
        }
    }
}
