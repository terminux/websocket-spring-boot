package com.ugrong.framework.ws.model;

import com.ugrong.framework.ws.api.WsMessageType;
import lombok.AllArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
public enum StandardMessageType implements WsMessageType, Serializable {

    P2P("点对点消息"), PUSH("服务器推送消息"),
    REPLY("应答消息"), EVENT("事件消息");

    private final String desc;

    @Override
    public String getType() {
        return this.name();
    }

    @Override
    public String getDesc() {
        return this.desc;
    }
}