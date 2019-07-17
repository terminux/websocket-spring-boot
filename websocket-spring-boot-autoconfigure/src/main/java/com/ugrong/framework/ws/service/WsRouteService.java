package com.ugrong.framework.ws.service;

import com.ugrong.framework.ws.model.WsMessage;

public interface WsRouteService {

    void handleMessage(WsMessage message);

}
