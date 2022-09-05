package com.rafaelbandim.network;

import org.springframework.stereotype.Service;

@Service
public interface Network {

    void addRequest(GameRequest gameRequest);

    void sendRequests();

    void readResponses();

}