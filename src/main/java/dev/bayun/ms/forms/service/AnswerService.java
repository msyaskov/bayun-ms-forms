package dev.bayun.ms.forms.service;

import dev.bayun.ms.forms.domain.Answer;
import dev.bayun.ms.forms.domain.core.Form;
import dev.bayun.ms.forms.repository.AnswerRepository;
import dev.bayun.ms.forms.repository.FormRepository;
import dev.bayun.ms.forms.security.AuthenticatedPrincipal;
import dev.bayun.ms.forms.validation.answer.AnswerValidator;
import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.UUID;

/**
 * @author Максим Яськов
 */

@Service
@AllArgsConstructor
public class AnswerService {

    private AnswerValidator answerValidator;

    private AnswerRepository answerRepository;

    private FormRepository formRepository;

    @NonNull
    public Answer create(String formId, Answer candidate) {
        Assert.notNull(formId, "A formId must not be null");
        Assert.notNull(candidate, "An candidate must not be null");

        Form form = findFormById(formId);

        answerValidator.validate(candidate, form);

        Answer answer = new Answer();
        answer.setFormId(form.getId());

        String principalId = SecurityContextHolder.getContext().getAuthentication().getName();
        answer.setAuthorId(principalId);

        answer.setValues(candidate.getValues());

        return answerRepository.save(answer);
    }

    public boolean existsByFormIdAuthorId(String formId, String authorId) {
        return answerRepository.existsByFormIdAndAuthorId(formId, authorId);
    }

    public List<Answer> findBy(String formId) {
        Assert.notNull(formId, "A formId must not be null");
        return answerRepository.findAllByFormId(formId);
    }

    public Answer findBySelf(String formId) {
        Assert.notNull(formId, "A formId must not be null");
        UUID id = ((AuthenticatedPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        return answerRepository.findByFormIdAndAuthorId(formId, id.toString()).orElseThrow();
    }

    @NonNull
    protected Form findFormById(String formId) {
        Assert.notNull(formId, "A formId must not be null");
        return formRepository.findById(formId).orElseThrow(() -> new FormNotFoundException("with id " + formId));
    }

}
