package com.bxgcloud.util;

import javax.websocket.Session;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by gaobin on 2016/11/30.
 */
public class SessionMap {
    private static Map<String,Session> sessionMap = new ConcurrentHashMap<String, Session>();

    public static Map<String, Session> getSessionMap() {
        return sessionMap;
    }

    public static void setSessionMap(Map<String, Session> sessionMap) {
        SessionMap.sessionMap = sessionMap;
    }
}
