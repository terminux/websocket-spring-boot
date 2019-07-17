package com.ugrong.framework.ws.model;

import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.security.Principal;

@AllArgsConstructor
public class SimpleWsPrincipal implements Principal, Serializable {

    private final String userId;

    @Override
    public String getName() {
        return this.userId;
    }
}
