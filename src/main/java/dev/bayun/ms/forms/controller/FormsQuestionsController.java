package dev.bayun.ms.forms.controller;

import dev.bayun.ms.forms.domain.core.Question;
import dev.bayun.ms.forms.domain.core.StringId;
import dev.bayun.ms.forms.domain.question.PatchQuestion;
import dev.bayun.ms.forms.rest.IdRestObject;
import dev.bayun.ms.forms.rest.form.QuestionRestObject;
import dev.bayun.ms.forms.service.QuestionService;
import dev.bayun.ms.forms.validation.group.OnCreate;
import dev.bayun.ms.forms.validation.group.OnUpdate;
import dev.bayun.sdk.rest.core.RestDocument;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Максим Яськов
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/forms/{formId}/questions")
public class FormsQuestionsController {

    private final QuestionService questionService;

    @ResponseStatus(HttpStatus.CREATED)
    @Validated(OnCreate.class)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public RestDocument post(@PathVariable String formId, @Valid @RequestBody Question newQuestion) {
        Question savedQuestion = this.questionService.create(formId, newQuestion);
        return RestDocument.builder()
                .object("questionId", new IdRestObject(new StringId(savedQuestion.getId())))
                .build();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/{questionId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public RestDocument getById(@PathVariable String formId, @PathVariable String questionId) {
        Question question = this.questionService.findBy(formId, questionId);
        return RestDocument.builder()
                .object("question", new QuestionRestObject(question))
                .build();
    }

    @ResponseStatus(HttpStatus.OK)
    @Validated(OnUpdate.class)
    @PutMapping(path = "/{questionId}",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void putById(@PathVariable String formId, @PathVariable String questionId, @Valid @RequestBody Question patch) {
        this.questionService.updateById(formId, questionId, patch);
    }

    @ResponseStatus(HttpStatus.OK)
    @Validated(OnUpdate.class)
    @PatchMapping(path = "/{questionId}",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void patchById(@PathVariable String formId, @PathVariable String questionId, @Valid @RequestBody PatchQuestion patch) {
        this.questionService.patchById(formId, questionId, patch);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(path = "/{questionId}")
    public void deleteById(@PathVariable String formId, @PathVariable String questionId) {
        this.questionService.deleteById(formId, questionId);
    }

}
