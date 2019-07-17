package com.ugrong.framework.ws.interceptor;

import lombok.AllArgsConstructor;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;

@AllArgsConstructor
public abstract class WsHandshakeHandler extends DefaultHandshakeHandler {

    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler handler, Map<String, Object> attr) {
        return getPrincipal((ServletServerHttpRequest) request, handler, attr);
    }

    abstract Principal getPrincipal(ServletServerHttpRequest request, WebSocketHandler handler, Map<String, Object> attr);
}
