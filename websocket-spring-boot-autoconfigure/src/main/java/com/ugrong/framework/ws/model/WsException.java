package com.ugrong.framework.ws.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WsException extends RuntimeException {

    private int code;

    private Object result;

    public WsException(int code) {
        this(code, "Encounter websocket exception.");
    }

    public WsException(int code, String message) {
        this(code, message, null);
    }

    public WsException(int code, String message, Object result) {
        super(message);
        this.code = code;
        this.result = result;
    }
}
