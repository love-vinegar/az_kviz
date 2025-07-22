package cz.personal.vinegar.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class WebSocketHandler extends TextWebSocketHandler {
    WebSocketSession boardSession = null;

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        if(payload.equals("board")) {
            boardSession = session;
            return;
        }

        if(boardSession == null) {
            return;
        }
        boardSession.sendMessage(new TextMessage(payload + "*" + "AbC"));

    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    }
}
