package com.ugrong.framework.ws.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RandomUtil {

    public static String generateUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
