package com.springboot.demo.springboot.webscoket;

import com.google.gson.Gson;

import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class WebSocketEncoder implements Encoder.Text<WebSocketMessage> {

    @Override
    public String encode(WebSocketMessage message) {
        return new Gson().toJson(message);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
    }

    @Override
    public void destroy() {
    }
}
