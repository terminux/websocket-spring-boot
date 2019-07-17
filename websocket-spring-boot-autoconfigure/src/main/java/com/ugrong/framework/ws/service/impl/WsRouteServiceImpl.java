package com.ugrong.framework.ws.service.impl;

import com.ugrong.framework.ws.api.WsMessageRouter;
import com.ugrong.framework.ws.model.WsMessage;
import com.ugrong.framework.ws.service.WsMessageService;
import com.ugrong.framework.ws.service.WsRouteService;
import lombok.AllArgsConstructor;

import java.util.Date;

@AllArgsConstructor
public class WsRouteServiceImpl implements WsRouteService {

    private final WsMessageRouter wsMessageRouter;

    private final WsMessageService wsMessageService;

    @Override
    public void handleMessage(WsMessage message) {
        message.setSendTime(new Date());
        wsMessageRouter.route(message, wsMessageService);
    }
}
