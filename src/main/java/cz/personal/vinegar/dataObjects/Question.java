package cz.personal.vinegar.dataObjects;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Question {
    String questionText;
    String questionCode;
    String answer;
    Boolean firstsPlayer;
}
