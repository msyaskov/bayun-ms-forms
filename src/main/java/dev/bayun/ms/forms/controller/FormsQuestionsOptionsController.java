package dev.bayun.ms.forms.controller;

import dev.bayun.ms.forms.domain.core.Option;
import dev.bayun.ms.forms.domain.core.PatchOption;
import dev.bayun.ms.forms.domain.core.StringId;
import dev.bayun.ms.forms.rest.IdRestObject;
import dev.bayun.ms.forms.rest.form.OptionRestObject;
import dev.bayun.ms.forms.service.OptionService;
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
@RequestMapping("/api/forms/{formId}/questions/{questionId}/options")
public class FormsQuestionsOptionsController {

    private final OptionService optionService;

    @ResponseStatus(HttpStatus.CREATED)
    @Validated(OnCreate.class)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public RestDocument post(@PathVariable String formId, @PathVariable String questionId, @Valid @RequestBody Option newOption) {
        Option savedOption = this.optionService.create(formId, questionId, newOption);
        return RestDocument.builder()
                .object("optionId", new IdRestObject(new StringId(savedOption.getId())))
                .build();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/{optionId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public RestDocument getById(@PathVariable String formId, @PathVariable String questionId, @PathVariable String optionId) {
        Option option = this.optionService.findBy(formId, questionId, optionId);
        return RestDocument.builder()
                .object("option", new OptionRestObject(option))
                .build();
    }

    @ResponseStatus(HttpStatus.OK)
    @Validated(OnUpdate.class)
    @PatchMapping(path = "/{optionId}",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void patchById(@PathVariable String formId, @PathVariable String questionId, @PathVariable String optionId, @Valid @RequestBody PatchOption patch) {
        this.optionService.patchBy(formId, questionId, optionId, patch);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(path = "/{optionId}")
    public void deleteById(@PathVariable String formId, @PathVariable String questionId, @PathVariable String optionId) {
        this.optionService.deleteById(formId, questionId, optionId);
    }

}
