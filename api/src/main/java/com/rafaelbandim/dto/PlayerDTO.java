package com.rafaelbandim.dto;


import java.util.ArrayList;
import java.util.List;

public class PlayerDTO {
    private Vector2DTO position;
    private String uuid;
    private float speed;

    public PlayerDTO() {

    }

    public PlayerDTO(Vector2DTO position, String uuid, float speed) {
        this.position = position;
        this.uuid = uuid;
        this.speed = speed;
    }

    public Vector2DTO getPosition() {
        return position;
    }

    public void setPosition(Vector2DTO position) {
        this.position = position;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public class Players {

    }
}
