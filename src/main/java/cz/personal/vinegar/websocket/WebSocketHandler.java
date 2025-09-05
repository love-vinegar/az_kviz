package cz.personal.vinegar.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.personal.vinegar.HexTriangle;
import cz.personal.vinegar.dataObjects.Question;
import cz.personal.vinegar.dataObjects.RequestDataItem;
import cz.personal.vinegar.services.QuestionService;
import java.util.Arrays;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import static cz.personal.vinegar.enums.Action.WIN;
import static cz.personal.vinegar.enums.Sender.BOARD;
import static cz.personal.vinegar.enums.Sender.READER;

@Component
@Slf4j
public class WebSocketHandler extends TextWebSocketHandler {
    WebSocketSession boardSession = null;
    WebSocketSession readerSession = null;

    QuestionService questionService;
    ObjectMapper objectMapper = new ObjectMapper();

    boolean isFirstPlayerTurn = true;
    String currentField = "";
    HexTriangle triangle = new HexTriangle(7);

    @Autowired
    public WebSocketHandler(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public void handleTextMessage(@NonNull WebSocketSession session,
                                  @NonNull TextMessage message) throws Exception {
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
                    String payload = dataItem.getPayload();
                    currentField = payload;
                    dataItem.setPayload(payload + "*" + questionCode);
                    boardSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(dataItem)));
                    dataItem.setPayload(question.getQuestionText() + "*" + question.getAnswer());
                    readerSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(dataItem)));
                    isFirstPlayerTurn = !isFirstPlayerTurn;
                }
                case MARK_FIELD -> {
                    String color = getColor(dataItem);
                    dataItem.setPayload(color);
                    int[] coords = HexTriangle.indexToRowCol(Integer.parseInt(currentField)-1);
                    log.info(Arrays.toString(coords));
                    triangle.setValue(coords[0], coords[1], color.equals("orange")?1:2);
                    boolean ahoj = triangle.hasConnectingPath(color.equals("orange")?1:2);
                    log.info(String.valueOf(ahoj));
                    boardSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(dataItem)));
                    readerSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(dataItem)));
                    if(ahoj) {
                        dataItem.setAction(WIN);
                        dataItem.setPayload(color);
                        boardSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(dataItem)));
                        readerSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(dataItem)));
                    }
                }
                case IDLE -> log.info("Received IDLE response");
                default -> log.error("Unknown action {}", dataItem.getAction());
            }

        } catch (Exception e) {
            log.error("Error while handling text message", e);
        }
    }

    private String getColor(RequestDataItem dataItem) {
        if(dataItem.getPayload().equals("no_answer")) {
            return "gray";
        }

        if(isFirstPlayerTurn) {
            if (dataItem.getPayload().equals("correct")) {
                return "blue";
            } else {
                return "orange";
            }
        } else {
            if (dataItem.getPayload().equals("correct")) {
                return "orange";
            } else {
                return "blue";
            }
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("Connection established to {}", session.getId());
    }

}
