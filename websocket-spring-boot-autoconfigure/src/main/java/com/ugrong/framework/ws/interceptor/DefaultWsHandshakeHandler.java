package com.ugrong.framework.ws.interceptor;

import com.ugrong.framework.ws.config.WsProperties;
import com.ugrong.framework.ws.model.SimpleWsPrincipal;
import com.ugrong.framework.ws.utils.WsValidator;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;

import java.security.Principal;
import java.util.Map;

@AllArgsConstructor
public class DefaultWsHandshakeHandler extends WsHandshakeHandler {

    private final WsProperties wsProperties;

    @Override
    Principal getPrincipal(ServletServerHttpRequest request, WebSocketHandler handler, Map<String, Object> attr) {
        WsValidator.isTrue(attr != null && !attr.isEmpty(), "Loss of user data.");

        Object principalId = attr.get(wsProperties.getPrincipalIdFieldName());
        WsValidator.isTrue(principalId != null && StringUtils.isNotBlank(((String) principalId)), "Missing principalId.");

        return new SimpleWsPrincipal((String) principalId);
    }
}
