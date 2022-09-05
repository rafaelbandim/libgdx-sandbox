package com.rafaelbandim.service;

import com.rafaelbandim.GameState;
import com.rafaelbandim.dto.MoveInputDTO;
import com.rafaelbandim.dto.PlayerDTO;
import com.rafaelbandim.network.*;
import org.springframework.stereotype.Service;

@Service
public class MoveService extends GenericRequest implements ResponseProcessor {
    private final GameState gameState;

    public MoveService(Network network, GameState gameState) {
        super(network);
        this.gameState = gameState;
    }

    public void move(int key) {
        MoveInputDTO moveInputDTO = new MoveInputDTO(key, gameState.getPlayer().getUuid());
        getNetwork().addRequest(new GameRequest(moveInputDTO, BodyType.MOVE));
    }

    @Override
    public void process(GameResponse gameResponse) {
        if (Status.SUCCESS.equals(gameResponse.getStatus())) {
            PlayerDTO playerDTO = (PlayerDTO) gameResponse.getBody();
            gameState.getPlayer().getPosition().set(playerDTO.getPosition().getX(), playerDTO.getPosition().getY());
        } else {
            throw new RuntimeException("Something went wrong");
        }
    }
}
