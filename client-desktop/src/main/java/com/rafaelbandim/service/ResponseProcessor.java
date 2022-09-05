package com.rafaelbandim.service;

import com.rafaelbandim.network.GameResponse;

public interface ResponseProcessor {
    void process(GameResponse gameResponse);
}
