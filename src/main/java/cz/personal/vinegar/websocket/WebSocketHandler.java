package cz.personal.vinegar.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.personal.vinegar.dataObjects.Question;
import cz.personal.vinegar.dataObjects.RequestDataItem;
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

            if(dataItem.getSender().equals(BOARD)) {
                log.info("Received BOARD response");
                boardSession = session;
            } else if (dataItem.getSender().equals(READER)) {
                log.info("Received READER response");
                readerSession = session;
            }

            if(boardSession == null || readerSession == null) {
                log.warn("Game is not ready");
                return;
            }

            log.info(dataItem.toString());
            switch(dataItem.getAction()) {
                case GET_QUESTION -> {
                    String questionCode = questionService.getQuestionCode(isFirstPlayerTurn, Integer.parseInt(dataItem.getPayload()));
                    Question question = questionService.getQuestion(isFirstPlayerTurn, Integer.parseInt(dataItem.getPayload()));
                    boardSession.sendMessage(new TextMessage(dataItem.getPayload() + "*" + questionCode));
                    readerSession.sendMessage(new TextMessage(question.getQuestionText() + "*" + question.getAnswer()));
                }
                case IDLE -> {
                    log.info("Received IDLE response");
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
