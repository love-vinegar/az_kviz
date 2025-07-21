package cz.personal.vinegar;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServerInterface {

    @GetMapping("/getQuestion")
    public String getQuestion(
            @RequestParam(name = "question") String question
    ) {
        return question;
    }

    @GetMapping("/getQuestionCode")
    public String test(
            @RequestParam(name = "questionCode") String questionCode
    ) {
        return questionCode;
    }
}
