package com.rafaelbandim.dto;

import java.io.Serializable;

public class Vector2DTO implements Serializable {
    private float x;
    private float y;

    public Vector2DTO() {
    }

    public Vector2DTO(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
