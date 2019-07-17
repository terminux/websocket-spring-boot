package com.ugrong.framework.ws.api;

import com.ugrong.framework.ws.model.WsException;
import com.ugrong.framework.ws.model.WsMessage;
import com.ugrong.framework.ws.service.WsMessageService;

public interface WsMessageHandler {

    void handle(WsMessage message, WsMessageService wsMessageService) throws WsException;
}
