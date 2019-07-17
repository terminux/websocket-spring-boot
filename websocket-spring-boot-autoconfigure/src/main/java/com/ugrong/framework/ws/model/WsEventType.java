package com.ugrong.framework.ws.model;

import lombok.AllArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
public enum WsEventType implements Serializable {

    CONNECTED("建立连接"), DISCONNECT("断开连接");

    private final String desc;

    public String getDesc() {
        return this.desc;
    }
}