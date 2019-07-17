package com.ugrong.framework.ws.api;

import com.ugrong.framework.ws.model.WsException;

public interface WsExceptionHandler {

    void handle(WsException e);

}
