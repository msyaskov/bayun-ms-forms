package dev.bayun.ms.forms.controller;

import dev.bayun.ms.forms.domain.core.Form;
import dev.bayun.ms.forms.domain.core.StringId;
import dev.bayun.ms.forms.rest.IdRestObject;
import dev.bayun.ms.forms.rest.form.FormMeta;
import dev.bayun.ms.forms.rest.form.FormRestObject;
import dev.bayun.ms.forms.rest.form.ListFormRestObject;
import dev.bayun.ms.forms.service.AnswerService;
import dev.bayun.ms.forms.service.FormService;
import dev.bayun.ms.forms.validation.group.OnCreate;
import dev.bayun.ms.forms.validation.group.OnUpdate;
import dev.bayun.sdk.rest.core.RestDocument;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Максим Яськов
 */

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/forms")
public class FormsController {

    private final FormService formService;

    private final AnswerService answerService;

    @ResponseStatus(HttpStatus.CREATED)
    @Validated(OnCreate.class)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public RestDocument post(@Valid @RequestBody Form newForm) {
        Form savedForm = formService.create(newForm);
        return RestDocument.builder()
                .object("formId", new IdRestObject(new StringId(savedForm.getId())))
                .build();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(params = "authorId",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public RestDocument getListByAuthorId(@RequestParam String authorId) {
        List<Form> forms = formService.findAllByAuthorId(authorId);
        return RestDocument.builder()
                .object("list_form", new ListFormRestObject(forms))
                .build();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/{formId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public RestDocument getById(@PathVariable String formId, Authentication authentication) {
        Form form = formService.findById(formId);
        FormMeta formMeta = FormMeta.builder()
                .replier(answerService.existsByFormIdAuthorId(formId, authentication.getName()))
                .build();

        return RestDocument.builder()
                .object("form", new FormRestObject(form, formMeta))
                .build();
    }

    @ResponseStatus(HttpStatus.OK)
    @Validated(OnUpdate.class)
    @PatchMapping(path = "/{formId}",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void patchById(@PathVariable String formId, @Valid @RequestBody Form patch) {
        formService.updateById(formId, patch);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(path = "/{formId}")
    public void deleteById(@PathVariable String formId) {
        formService.deleteById(formId);
    }

}
