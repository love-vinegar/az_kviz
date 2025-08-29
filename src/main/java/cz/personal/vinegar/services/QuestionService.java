package cz.personal.vinegar.services;

import cz.personal.vinegar.dataObjects.Question;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class QuestionService {

    List<Question> questionsFirstPlayer;
    List<Question> questionsSecondPlayer;

    public QuestionService () {
        fillQuestions();
    }

    public void fillQuestions() {
        //todo this will be replaced by a database call
        questionsFirstPlayer = List.of(
                new Question("1Question", "a", "aAnswer", true),
                new Question("2Question", "b", "bAnswer", true),
                new Question("3Question", "c", "cAnswer", true),
                new Question("4Question", "d", "dAnswer", true),
                new Question("5Question", "e", "eAnswer", true),
                new Question("6Question", "f", "fAnswer", true),
                new Question("7Question", "g", "gAnswer", true),
                new Question("8Question", "h", "hAnswer", true),
                new Question("9Question", "i", "iAnswer", true),
                new Question("10Question", "a", "aAnswer", true),
                new Question("11Question", "b", "bAnswer", true),
                new Question("12Question", "c", "cAnswer", true),
                new Question("13Question", "d", "dAnswer", true),
                new Question("14Question", "e", "eAnswer", true),
                new Question("15Question", "f", "fAnswer", true),
                new Question("16Question", "g", "gAnswer", true),
                new Question("17Question", "h", "hAnswer", true),
                new Question("18Question", "i", "iAnswer", true),
                new Question("19Question", "a", "aAnswer", true),
                new Question("20Question", "b", "bAnswer", true),
                new Question("21Question", "c", "cAnswer", true),
                new Question("22Question", "d", "dAnswer", true),
                new Question("23Question", "e", "eAnswer", true),
                new Question("24Question", "f", "fAnswer", true),
                new Question("25Question", "g", "gAnswer", true),
                new Question("26Question", "h", "hAnswer", true),
                new Question("27Question", "i", "iAnswer", true),
                new Question("28Question", "a", "aAnswer", true)
        );

        questionsSecondPlayer = List.of(
                new Question("1AQuestion", "a", "aAnswer", false),
                new Question("2AQuestion", "b", "bAnswer", false),
                new Question("3AQuestion", "c", "cAnswer", false),
                new Question("4AQuestion", "d", "dAnswer", false),
                new Question("5AQuestion", "e", "eAnswer", true),
                new Question("6AQuestion", "f", "fAnswer", true),
                new Question("7AQuestion", "g", "gAnswer", true),
                new Question("8AQuestion", "h", "hAnswer", true),
                new Question("9AQuestion", "i", "iAnswer", true),
                new Question("10A0Question", "a", "aAnswer", true),
                new Question("11AQuestion", "b", "bAnswer", false),
                new Question("12AQuestion", "c", "cAnswer", false),
                new Question("13AQuestion", "d", "dAnswer", false),
                new Question("14AQuestion", "e", "eAnswer", false),
                new Question("15AQuestion", "f", "fAnswer", false),
                new Question("16AQuestion", "g", "gAnswer", false),
                new Question("17AQuestion", "h", "hAnswer", false),
                new Question("18AQuestion", "i", "iAnswer", false),
                new Question("19AQuestion", "a", "aAnswer", false),
                new Question("20AQuestion", "b", "bAnswer", false),
                new Question("21AQuestion", "c", "cAnswer", false),
                new Question("22AQuestion", "d", "dAnswer", false),
                new Question("23AQuestion", "e", "eAnswer", false),
                new Question("24AQuestion", "f", "fAnswer", false),
                new Question("25AQuestion", "g", "gAnswer", false),
                new Question("26AQuestion", "h", "hAnswer", false),
                new Question("27AQuestion", "i", "iAnswer", false),
                new Question("28AQuestion", "a", "aAnswer", false)
        );
    }

    public Question getQuestion(boolean firstPlayer, int questionIndex) {
        questionIndex -= 1;
        if(questionIndex < 0 || questionIndex >= questionsFirstPlayer.size()) {
            log.error("Invalid question index");
            return null;
        }

        if(firstPlayer) {
            return questionsFirstPlayer.get(questionIndex);
        } else {
            return questionsSecondPlayer.get(questionIndex);
        }
    }

    public String getQuestionCode(boolean firstPlayer, int questionIndex) {
        questionIndex -= 1;
        if(questionIndex < 0 || questionIndex >= questionsFirstPlayer.size()) {
            log.error("Invalid question index");
            return null;
        }

        if(firstPlayer) {
            return questionsFirstPlayer.get(questionIndex).getQuestionCode();
        } else {
            return questionsSecondPlayer.get(questionIndex).getQuestionCode();
        }
    }
}
