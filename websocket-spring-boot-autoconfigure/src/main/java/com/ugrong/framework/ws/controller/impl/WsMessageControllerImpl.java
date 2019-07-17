package com.ugrong.framework.ws.controller.impl;

import com.ugrong.framework.ws.controller.WsMessageController;
import com.ugrong.framework.ws.model.WsMessage;
import com.ugrong.framework.ws.service.WsRouteService;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

@AllArgsConstructor
@Controller
public class WsMessageControllerImpl implements WsMessageController {

    private final WsRouteService wsRouteService;

    @Override
    @MessageMapping("${app.websocket.message-suffix:/message}")
    //@SendTo("/topic/public")
    public void listenMessage(@Payload WsMessage message) {
        wsRouteService.handleMessage(message);
    }
}