package com.rafaelbandim.service;

import com.rafaelbandim.network.Network;

public abstract class GenericRequest {
    private final Network network;

    public GenericRequest(Network network) {
        this.network = network;
    }

    protected Network getNetwork() {
        return network;
    }
}
