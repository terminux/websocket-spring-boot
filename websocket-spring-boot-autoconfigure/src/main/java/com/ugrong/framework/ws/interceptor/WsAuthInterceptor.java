package com.ugrong.framework.ws.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.Assert;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Slf4j
public abstract class WsAuthInterceptor implements HandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse response,
                                   WebSocketHandler handler, Map<String, Object> attr) {
        try {
            Assert.isTrue(serverHttpRequest instanceof ServletServerHttpRequest, "Illegal requests.");
            HttpServletRequest request = ((ServletServerHttpRequest) serverHttpRequest).getServletRequest();
            return doAuth(request, attr);
        } catch (Exception e) {
            log.error("Intercept illegal requests.{}", e);
        }
        return false;
    }

    abstract boolean doAuth(HttpServletRequest request, Map<String, Object> attr);

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
                               WebSocketHandler handler, Exception e) {
    }
}
