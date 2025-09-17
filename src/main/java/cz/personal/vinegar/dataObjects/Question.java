package cz.personal.vinegar.dataObjects;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Question {
    public Question(String question, String answer, String code) {
        this.questionText  = question;
        this.answer = answer;
        this.questionCode = code;
    }
    String questionText;
    String questionCode;
    String answer;
    Boolean firstsPlayer;
}
