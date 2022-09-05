package com.rafaelbandim.network;

public class GameResponse extends GenericPayload {
    private Status status;

    public GameResponse() {
    }

    public GameResponse(Object body, BodyType bodyType, Status status) {
        super(body, bodyType);
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
