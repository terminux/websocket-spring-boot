package com.ugrong.framework.ws.handler;

import com.ugrong.framework.ws.api.WsExceptionHandler;
import com.ugrong.framework.ws.model.WsException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogExceptionHandler implements WsExceptionHandler {

    @Override
    public void handle(WsException e) {
        log.error("Error happens.{}", e);
    }
}