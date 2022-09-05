package com.rafaelbandim.dto;

public class MoveInputDTO {
    private Integer direction;
    private String uuid;

    public  MoveInputDTO(){}

    public MoveInputDTO(Integer direction, String uuid) {
        this.direction = direction;
        this.uuid = uuid;
    }

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
