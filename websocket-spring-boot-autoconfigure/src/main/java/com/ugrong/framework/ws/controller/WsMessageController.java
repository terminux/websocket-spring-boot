package com.ugrong.framework.ws.controller;


import com.ugrong.framework.ws.model.WsMessage;

public interface WsMessageController {

    void listenMessage(WsMessage message);
}
