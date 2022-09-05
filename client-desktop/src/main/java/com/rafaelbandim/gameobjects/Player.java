package com.rafaelbandim.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

public class Player {

    private final TextureAtlas playerAtlas;
    private Animation<TextureRegion> walkingUpAnimation;
    private Animation<TextureRegion> walkingDownAnimation;
    private Animation<TextureRegion> walkingLeftAnimation;
    private Animation<TextureRegion> walkingRightAnimation;
    private Array<TextureAtlas.AtlasRegion> walkingFrames;
    float stateTime;
    private TextureRegion currentFrame;
    private TextureRegion lastFrame;
    private Vector2 position;
    private String uuid;
    private Color color;


    public Player(TextureAtlas playerAtlas) {
        this.playerAtlas = playerAtlas;
        stateTime = 0;
        walkingUpAnimation = new Animation(0.3f, playerAtlas.findRegions("up"), Animation.PlayMode.LOOP);
        walkingDownAnimation = new Animation(0.3f, playerAtlas.findRegions("down"), Animation.PlayMode.LOOP);
        walkingLeftAnimation = new Animation(0.3f, playerAtlas.findRegions("left"), Animation.PlayMode.LOOP);
        walkingRightAnimation = new Animation(0.3f, playerAtlas.findRegions("right"), Animation.PlayMode.LOOP);
        lastFrame = playerAtlas.findRegion("down");
        currentFrame = walkingDownAnimation.getKeyFrame(stateTime, false);
        Random random = new Random();
        this.color = new Color(random.nextInt(2), random.nextInt(2), random.nextInt(2), 1);
    }

    public Player(TextureAtlas playerAtlas, Vector2 position, String uuid) {
        this(playerAtlas);
        this.position = position;
        this.uuid = uuid;
    }

    public void draw(SpriteBatch spriteBatch) {
        stateTime += Gdx.graphics.getDeltaTime();
        spriteBatch.setColor(color);
        spriteBatch.draw(currentFrame, getPosition().x, getPosition().y);
        currentFrame = lastFrame;
    }

    public void setAnimation(int key) {
        switch (key) {
            case Input.Keys.UP: {
                currentFrame = walkingUpAnimation.getKeyFrame(stateTime, true);
                lastFrame = walkingUpAnimation.getKeyFrame(stateTime, false);
                break;
            }
            case Input.Keys.DOWN: {
                currentFrame = walkingDownAnimation.getKeyFrame(stateTime, true);
                lastFrame = walkingDownAnimation.getKeyFrame(stateTime, false);
                break;
            }
            case Input.Keys.RIGHT: {
                currentFrame = walkingRightAnimation.getKeyFrame(stateTime, true);
                lastFrame = walkingRightAnimation.getKeyFrame(stateTime, false);
                break;
            }
            case Input.Keys.LEFT: {
                currentFrame = walkingLeftAnimation.getKeyFrame(stateTime, true);
                lastFrame = walkingLeftAnimation.getKeyFrame(stateTime, false);
            }
        }
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

}
