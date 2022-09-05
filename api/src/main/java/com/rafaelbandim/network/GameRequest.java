package com.rafaelbandim.network;

public class GameRequest extends GenericPayload {

    public GameRequest() {
    }

    public GameRequest(Object body, BodyType gameBodyType) {
        super(body, gameBodyType);
    }
}
