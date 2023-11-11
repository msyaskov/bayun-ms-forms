package dev.bayun.ms.forms.controller;

import dev.bayun.ms.forms.domain.Answer;
import dev.bayun.ms.forms.domain.core.StringId;
import dev.bayun.ms.forms.rest.IdRestObject;
import dev.bayun.ms.forms.rest.ListAnswerRestObject;
import dev.bayun.ms.forms.rest.form.AnswerRestObject;
import dev.bayun.ms.forms.service.AnswerService;
import dev.bayun.sdk.rest.core.RestDocument;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Максим Яськов
 */

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/forms/{formId}/answers")
public class FormsAnswersController {

    private AnswerService answerService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public RestDocument postAnswer(@PathVariable String formId, @RequestBody Answer newAnswer) {
        Answer answer = answerService.create(formId, newAnswer);
        return RestDocument.builder()
                .object("answerId", new IdRestObject(new StringId(answer.getId())))
                .build();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public RestDocument getAnswers(@PathVariable String formId) {
        List<Answer> answers = answerService.findBy(formId);
        return RestDocument.builder()
                .object("list_answer", new ListAnswerRestObject(answers))
                .build();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path= "/self", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestDocument getSelfAnswer(@PathVariable String formId) {
        Answer self = answerService.findBySelf(formId);
        return RestDocument.builder()
                .object("answer", new AnswerRestObject(self))
                .build();
    }

}
