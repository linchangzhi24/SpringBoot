package com.springboot.demo.springboot.webscoket;

import com.springboot.demo.springboot.common.response.Response;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class WebSocketController {

    @RequestMapping(value = "/test")
    public Object test() {
        HashMap<String, String> data = new HashMap<>();
        data.put("result", "success");
        return Response.success(data);
    }

    @ResponseBody
    @RequestMapping(value = "/api/websocket/push/{fromId}", method = RequestMethod.POST)
    public Object sendMessageToPeer(@PathVariable String fromId, @RequestBody Map<String, String> requestBody) {
        String toId = requestBody.get("toId");
        String message = requestBody.get("message");
        WebSocketMessage webSocketMessage = new WebSocketMessage();
        webSocketMessage.setFromId(fromId);
        webSocketMessage.setToId(toId);
        webSocketMessage.setMessage(message);
        WebSocketServer.sendMessageToPeer(webSocketMessage);
        return Response.success(webSocketMessage);
    }
}
