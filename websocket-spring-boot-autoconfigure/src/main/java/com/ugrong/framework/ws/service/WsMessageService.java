package com.ugrong.framework.ws.service;

public interface WsMessageService {

    void broadcast(Object payload);

    void sendToUser(String userId, Object payload);
}
