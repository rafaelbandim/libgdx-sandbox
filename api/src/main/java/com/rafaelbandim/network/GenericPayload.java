package com.rafaelbandim.network;

abstract class GenericPayload {
    private Object body;
    private BodyType bodyType;

    public GenericPayload() {
    }

    public GenericPayload(Object body, BodyType bodyType) {
        this.body = body;
        this.bodyType = bodyType;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public BodyType getBodyType() {
        return bodyType;
    }

    public void setBodyType(BodyType bodyType) {
        this.bodyType = bodyType;
    }
}
