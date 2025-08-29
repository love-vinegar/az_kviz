package cz.personal.vinegar.services;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class QuestionService {

    List<Pair<String, String>> questions_first_player;
    List<Pair<String, String>> questions_second_player;

    public QuestionService () {
        fillQuestions();
    }

    public void fillQuestions() {
        //todo this will be replaced by a database call
        questions_first_player = List.of(
                Pair.of("1Question", "a"),
                Pair.of("1Question", "b"),
                Pair.of("1Question", "c"),
                Pair.of("1Question", "d"),
                Pair.of("1Question", "e"),
                Pair.of("1Question", "f"),
                Pair.of("1Question", "g"),
                Pair.of("1Question", "h"),
                Pair.of("1Question", "i"),
                Pair.of("1Question", "a"),
                Pair.of("1Question", "b"),
                Pair.of("1Question", "c"),
                Pair.of("1Question", "d"),
                Pair.of("1Question", "e"),
                Pair.of("1Question", "f"),
                Pair.of("1Question", "g"),
                Pair.of("1Question", "h"),
                Pair.of("1Question", "i"),
                Pair.of("1Question", "a"),
                Pair.of("1Question", "b"),
                Pair.of("1Question", "c"),
                Pair.of("1Question", "d"),
                Pair.of("1Question", "e"),
                Pair.of("1Question", "f"),
                Pair.of("1Question", "g"),
                Pair.of("1Question", "h"),
                Pair.of("1Question", "i"),
                Pair.of("1Question", "a")
        );

        questions_second_player = List.of(
                Pair.of("2Question", "a"),
                Pair.of("2Question", "b"),
                Pair.of("2Question", "c"),
                Pair.of("2Question", "d"),
                Pair.of("2Question", "e"),
                Pair.of("2Question", "f"),
                Pair.of("2Question", "g"),
                Pair.of("2Question", "h"),
                Pair.of("2Question", "i"),
                Pair.of("2Question", "a"),
                Pair.of("2Question", "b"),
                Pair.of("2Question", "c"),
                Pair.of("2Question", "d"),
                Pair.of("2Question", "e"),
                Pair.of("2Question", "f"),
                Pair.of("2Question", "g"),
                Pair.of("2Question", "h"),
                Pair.of("2Question", "i"),
                Pair.of("2Question", "a"),
                Pair.of("2Question", "b"),
                Pair.of("2Question", "c"),
                Pair.of("2Question", "d"),
                Pair.of("2Question", "e"),
                Pair.of("2Question", "f"),
                Pair.of("2Question", "g"),
                Pair.of("2Question", "h"),
                Pair.of("2Question", "i"),
                Pair.of("2Question", "a")
        );
    }

    public String getQuestion(boolean firstPlayer, int questionIndex) {
        questionIndex -= 1;
        if(questionIndex < 0 || questionIndex >= questions_first_player.size()) {
            log.error("Invalid question index");
            return null;
        }

        if(firstPlayer) {
            return questions_first_player.get(questionIndex).getLeft();
        } else {
            return questions_second_player.get(questionIndex).getLeft();
        }
    }

    public String getQuestionCode(boolean firstPlayer, int questionIndex) {
        questionIndex -= 1;
        if(questionIndex < 0 || questionIndex >= questions_first_player.size()) {
            log.error("Invalid question index");
            return null;
        }

        if(firstPlayer) {
            return questions_first_player.get(questionIndex).getRight();
        } else {
            return questions_second_player.get(questionIndex).getRight();
        }
    }
}
