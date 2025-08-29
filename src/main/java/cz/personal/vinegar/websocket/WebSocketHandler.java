package cz.personal.vinegar.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.personal.vinegar.dataObjects.RequestDataItem;
import cz.personal.vinegar.enums.Sender;
import cz.personal.vinegar.services.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import static cz.personal.vinegar.enums.Sender.BOARD;
import static cz.personal.vinegar.enums.Sender.READER;

@Component
@Slf4j
public class WebSocketHandler extends TextWebSocketHandler {
    WebSocketSession boardSession = null;
    WebSocketSession readerSession = null;

    @Autowired
    QuestionService questionService;
    ObjectMapper objectMapper = new ObjectMapper();

    boolean isFirstPlayerTurn = true;

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        try {
            RequestDataItem dataItem = objectMapper.readValue(message.getPayload(), RequestDataItem.class);

            if(dataItem.getSender().equals(BOARD) && boardSession == null) {
                log.info("Received BOARD response");
                boardSession = session;
                return;
            } else if (dataItem.getSender().equals(READER) && readerSession == null) {
                log.info("Received READER response");
                readerSession = session;
                return;
            }

            if(boardSession == null || readerSession == null) {
                log.warn("Session is null");
                return;
            }

            log.info(dataItem.toString());
            switch(dataItem.getAction()) {
                case GET_QUESTION -> {
                    String questionCode = questionService.getQuestionCode(isFirstPlayerTurn, Integer.parseInt(dataItem.getPayload()));
                    String question = questionService.getQuestion(isFirstPlayerTurn, Integer.parseInt(dataItem.getPayload()));
                    boardSession.sendMessage(new TextMessage(dataItem.getPayload() + "*" + questionCode));
                    readerSession.sendMessage(new TextMessage(question));
                }
                default -> {
                    log.error("Unknown action {}", dataItem.getAction());
                }
            }

        } catch (Exception e) {
            log.error("Error while handling text message", e);
            return;
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    }
}
